/**   
* @Title: ApiTest.java 
* @Package com.zt.apidemo.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zting   
* @date 2020年3月30日 上午11:10:04 
  
*/
package com.zt.apidemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zt.apidemo.anno.MyApiLogAnno;


/** api 访问demo
* @ClassName: ApiTest 
* @Description: TODO
* @author zting 
* @date 2020年3月30日 上午11:10:04 
*  
*/
@Controller
@RequestMapping("/test")
public class ApiTest {
	@GetMapping("")
	@ResponseBody
	@MyApiLogAnno
	public Object test() {
		return "test";
	}
}
