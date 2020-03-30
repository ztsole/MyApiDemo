/**   
* @Title: WebConfig.java 
* @Package com.wd.forestfirepreventionapi.config 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zting   
* @date 2020年1月2日 上午11:48:12 
  
*/
package com.zt.apidemo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/** web配置
* @ClassName: WebConfig 
* @Description: 包含请求地址拦截器，文件请求映射等
* @author zting 
* @date 2020年1月2日 上午11:48:12 
*  
*/
@SuppressWarnings("deprecation")
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	
	@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // TODO Auto-generated method stub
        super.configureMessageConverters(converters);
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat
        );
        //处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        
        fastConverter.setFastJsonConfig(fastJsonConfig);
        
        converters.add(fastConverter);
    }

    @Autowired
	private  FileUploadConfig fileUploadConfig;
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(fileUploadConfig.getServerFilePath()).addResourceLocations("file:///"+fileUploadConfig.getUploadPath());
		
	}

}
