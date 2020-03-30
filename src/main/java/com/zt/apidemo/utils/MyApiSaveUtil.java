/**   
* @Title: MyApiSaveUtil.java 
* @Package com.wd.forestfirepreventionapi.tool 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zting   
* @date 2020年2月17日 上午11:50:23 
  
*/
package com.zt.apidemo.utils;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.zt.apidemo.config.FileUploadConfig;
import com.zt.apidemo.vo.ApiLogSaveVO;


/** 
* @ClassName: MyApiSaveUtil 
* @Description: 接口请求地址日志存储
* @author zting 
* @date 2020年2月17日 上午11:50:23 
*  
*/
@Component
public class MyApiSaveUtil {
	@Autowired
	private  FileUploadConfig fileUploadConfig;

	public String apiSave(ApiLogSaveVO asv,String fileName) {
		String url="";
		String[] classNameArr= asv.getClassName().split("\\.");
		//文件统一存储路径+年+月+日+时+接口用户+类名+方法名+文件名
		String path=fileUploadConfig.getApiLogPath();
		path+=DateUtil.GetCurrentDateFormat("yyyy/MM/dd/HH");
		path+="/"+classNameArr[classNameArr.length-1]+"/"+asv.getMethodName();
		fileName=fileName+"-"+DateUtil.GetCurrentDateFormat("yyyy_MM_dd_HHmmss_SSS");
		
		File targetFile = new File(path);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		
		url=path+"/"+fileName+".json";
		if(FileUtil.saveFile(JSONObject.toJSONString(asv),url)) {
			return url;
		};
		
		return "";
	}
}
