/**   
* @Title: AspectOpt.java 
* @Package com.zt.apidemo.vo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zting   
* @date 2020年3月30日 下午12:01:06 
  
*/
package com.zt.apidemo.vo;

/** 
* @ClassName: AspectOpt 
* @Description: TODO
* @author zting 
* @date 2020年3月30日 下午12:01:06 
*  
*/
public enum AspectOptEnum {
	before("before","before"),
	afterReturning("AfterReturning","AfterReturning"),
	after("after","after"),
	afterThrowing("afterThrowing","afterThrowing");
	
	private String code;
	private String msg;
	AspectOptEnum(String code,String msg){
		this.code=code;
		this.msg=msg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
