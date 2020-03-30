/** 返回数据格式
* @Title: AjaxResult.java 
* @Package com.wd.forestfirepreventionapi.vo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zting   
* @date 2020年1月2日 下午2:42:30 
  
*/
package com.zt.apidemo.vo;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.zt.apidemo.utils.StringUtil;

/** 
* @ClassName: AjaxResult 
* @Description: TODO
* @author zting 
* @date 2020年1月2日 下午2:42:30 
*  
*/
public class AjaxResult extends HashMap<String, Object>{
	private static final long serialVersionUID = 1L;

	public AjaxResult() {
		put("code", ApiCodeEnum.success.getCode());
		put("msg", ApiCodeEnum.success.getMsg());
		put("data",null);
	}

	public static AjaxResult error() {
		return error(ApiCodeEnum.error_other, ApiCodeEnum.error_other.getMsg());
	}

	public static AjaxResult error(String msg) {
		return error(ApiCodeEnum.error_other, msg);
	}

	public static AjaxResult error(ApiCodeEnum apiCode, String msg) {
		AjaxResult r = new AjaxResult();
		r.put("code", apiCode.getCode());
		r.put("msg", StringUtil.isEmpty(msg)?apiCode.getMsg():msg);
		r.put("data", null);
		return r;
	}
	public static AjaxResult error(ApiCodeEnum apiCode) {
		AjaxResult r = new AjaxResult();
		r.put("code", apiCode.getCode());
		r.put("msg", apiCode.getMsg());
		r.put("data", null);
		return r;
	}
	
	public static AjaxResult putOKData( Object obj) {
		AjaxResult r = new AjaxResult();
		r.put("data", obj);
		return r;
	}
	
	public static AjaxResult ok(String msg) {
		AjaxResult r = new AjaxResult();
		r.put("msg", msg);
		return r;
	}

	public static AjaxResult ok(Map<String, Object> map) {
		AjaxResult r = new AjaxResult();
		r.putAll(map);
		return r;
	}

	public static AjaxResult ok() {
		return new AjaxResult();
	}

	@Override
	public AjaxResult put(String key, Object value) {
		super.put(key, value);
		return this;
	}
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
