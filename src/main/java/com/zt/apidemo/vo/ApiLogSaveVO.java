/**   
* @Title: ApiLogSaveVO.java 
* @Package com.wd.forestfirepreventionapi.vo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zting   
* @date 2020年2月17日 上午11:35:58 
  
*/
package com.zt.apidemo.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * @ClassName: ApiLogSaveVO
 * @Description: api 文档存储json格式
 * @author zting
 * @date 2020年2月17日 上午11:35:58
 * 
 */
@Data
public class ApiLogSaveVO {
	//类名
	private String className;
	// 方法名
	private String methodName;
	//用户ip(请求地址)
	private String userIp;
	// 请求参数
	private List<String> reqParam;
	// 返回参数
	private Object retData;
	// api 用户 
	private String ApiAccount;
	// 时间
	private Date date;

}
