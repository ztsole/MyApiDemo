package com.zt.apidemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix="file-upload")
@Data
public class FileUploadConfig {
	//上传路径
	private String uploadPath;
	private String serverSaveFilePath;
	private String serverFilePath;
	//api 上传地址
	private String apiLogPath;

}
