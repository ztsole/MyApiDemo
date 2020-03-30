/**   
* @Title: ApiCodeEnum.java 
* @Package com.zt.apidemo.vo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zting   
* @date 2020年3月30日 上午11:27:05 
  
*/
package com.zt.apidemo.vo;

/** 
* @ClassName: ApiCodeEnum 
* @Description: TODO
* @author zting 
* @date 2020年3月30日 上午11:27:05 
*  
*/
public enum ApiCodeEnum {
	success(0,"请求成功"),
	error_param(210,"参数错误"),
	error_business(220,"业务错误"),
	error_other(210,"其他错误",1),
	error_sys(500,"系统错误",1),
	error_un(999,"未知错误",1)
	;
	//代码
	private Integer code;
	//错误描述
	private String msg;
	
	/** 级别
	 * 1 系统内部错误，错误信息不对外展示
	 * 2 非系统内部错误，错误信息可以对外展示
	 */
	private Integer level=2;
	
	ApiCodeEnum(Integer code,String msg){
		this.code=code;
		this.msg=msg;
	}
	
	ApiCodeEnum(Integer code,String msg,Integer level){
		this.code=code;
		this.msg=msg;
		this.level=level;
	}
	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public  static ApiCodeEnum getByCode(Integer code) {
		for(ApiCodeEnum e: ApiCodeEnum.values()) {
			if(e.getCode().equals(code)) {
				return e;
			}
		}
		return null;
		
	}
	public static Boolean isLevel1(Integer code) {
		ApiCodeEnum e=getByCode(code);
		return e!=null&&e.getLevel()==1?true:false;
	}
}
