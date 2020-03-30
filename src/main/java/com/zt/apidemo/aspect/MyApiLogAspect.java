/**   
* @Title: MyApiLogAspect.java 
* @Package com.zt.apidemo.aspect 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zting   
* @date 2020年3月30日 上午11:17:12 
  
*/
package com.zt.apidemo.aspect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.zt.apidemo.anno.MyApiLogAnno;
import com.zt.apidemo.utils.IpAdrressUtil;
import com.zt.apidemo.utils.MyApiSaveUtil;
import com.zt.apidemo.utils.StringUtil;
import com.zt.apidemo.vo.AjaxResult;
import com.zt.apidemo.vo.ApiCodeEnum;
import com.zt.apidemo.vo.ApiLogSaveVO;
import com.zt.apidemo.vo.AspectOptEnum;
/** 
* @ClassName: MyApiLogAspect 
* @Description: TODO
* @author zting 
* @date 2020年3月30日 上午11:17:12 
*  
*/
@Aspect // 使用@Aspect注解声明一个切面
@Component
public class MyApiLogAspect {
	@Value("${myApiLogAspect.isFilterInsideError}")
	private Boolean isFilterInsideError;

	@Autowired
	private  MyApiSaveUtil myApiSaveUtil;
	
	/**
	 * 这里我们使用注解的形式 当然，我们也可以通过切点表达式直接指定需要拦截的package,需要拦截的class 以及 method 切点表达式:
	 * execution(...)
	 */
	@Pointcut("@annotation(com.zt.apidemo.anno.MyApiLogAnno)")
	public void logPointCut() {
	}

	@AfterReturning(returning = "ret", pointcut = "logPointCut()")
	public void AfterReturning(JoinPoint joinPoint, Object ret) {
		MyLog(joinPoint, AspectOptEnum.afterReturning,ret);
	}

	// @After("logPointCut()")
	public void after(JoinPoint joinPoint) {

		//MyLog(joinPoint, AspectOpt.after);

	}

	@AfterThrowing("logPointCut()")
	public void afterThrowing(JoinPoint joinPoint) {
		MyLog(joinPoint, AspectOptEnum.after);
	}
	
	/**日志存储
	 * 
	 * @author zting
	 * @date 2020年2月18日 下午5:03:40
	 * @Description: 日志存储
	 * @param joinPoint 
	 * @param aspectOpt 返回结果对象
	 * @param ret
	 * @return void
	 *
	 */
	private void MyLog(JoinPoint joinPoint, AspectOptEnum aspectOpt,Object ret) {
		
		try {
			ApiLogSaveVO ASV=new ApiLogSaveVO();
			ASV.setDate(new Date());
			
			// 请求参数
			List<String> requestParam=new ArrayList<String>();
			for (Object o : joinPoint.getArgs()) {
				
				if(o instanceof MultipartFile|| o instanceof MultipartFile[]) {
					requestParam.add(new Gson().toJson(o));
				}
				else {
					requestParam.add(JSONObject.toJSONStringWithDateFormat(o, "yyyy-MM-dd hh:mm:ss"));
				}
				
			}
			ASV.setReqParam(requestParam);
			
		
			
		  MethodSignature signature = (MethodSignature) joinPoint.getSignature(); 
		 
		
		  //请求的 类名、方法名 
		  String className = joinPoint.getTarget().getClass().getName();
		  ASV.setClassName(className);
		  ASV.setMethodName(signature.getName());
		  ASV.setRetData(ret);
		  
		  // request 对象
		  ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		  HttpServletRequest request = attributes.getRequest();
		  ASV.setApiAccount(request.getHeader("account"));
		  ASV.setUserIp(IpAdrressUtil.getIpAdrress(request));
		  
		  Method method =signature.getMethod(); 
		  String fileName=method.getName();
		  MyApiLogAnno myApiLog=method.getAnnotation(MyApiLogAnno.class); 
		  //注解功能处理,根据注解值进行自定义处理
		  if(myApiLog != null){ 
			  if(!StringUtil.isSpanceOrEmpty(myApiLog.fileName())) {
				  fileName=myApiLog.fileName();
			  }
		  } 
		 // 日志存储
		 myApiSaveUtil.apiSave(ASV,fileName);
		  
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			 // 返回结果异常描述处理,以便看到系统内容错误
			  if(isFilterInsideError==true&&ret.getClass()==AjaxResult.class) {
				  AjaxResult aj=(AjaxResult)ret;
				  ApiCodeEnum em=ApiCodeEnum.getByCode(Integer.parseInt(StringUtil.objToString(aj.get("code"))));
				  //对于level=1的返回信息进行屏蔽
				  if(em!=null&&em.getLevel().equals(1)) {
					  ((AjaxResult)ret).put("msg",ApiCodeEnum.error_un.getMsg());
				  } 
			  }
		} catch (Exception e) {
			System.out.println("MyApiLogAspect - ajax error");
			e.printStackTrace();
		}
	 
	}
	private void MyLog(JoinPoint joinPoint, AspectOptEnum aspectOpt) {
		MyLog(joinPoint,aspectOpt,null);
	}


}
