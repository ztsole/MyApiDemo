/**   
* @Title: MyApiLogAnno.java 
* @Package com.zt.apidemo.anno 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zting   
* @date 2020年3月30日 上午11:18:04 
  
*/
package com.zt.apidemo.anno;

/** 自定义日志注解
* @ClassName: MyApiLogAnno 
* @Description: TODO
* @author zting 
* @date 2020年3月30日 上午11:18:04 
*  
*/
public @interface MyApiLogAnno {
	//文件名
	 String fileName() default "";
}
