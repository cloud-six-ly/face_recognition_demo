package com.dbgs.face_recognition_demo;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan("com.dbgs,com.baidu,com.chengdu")
@EnableJpaRepositories(basePackages="com.dbgs.repository")
@EnableJpaAuditing
@EntityScan(basePackages="com.dbgs.entity")
@EnableTransactionManagement
@EnableCaching
public class FaceRecognitionDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FaceRecognitionDemoApplication.class, args);
	}
	
	@Bean
	public MultipartConfigElement multipartConfigElement() {
	MultipartConfigFactory factory = new MultipartConfigFactory();
	// 单个数据大小
	factory.setMaxFileSize("100MB"); // KB,MB
	/// 总上传数据大小
	factory.setMaxRequestSize("999999MB");
	return factory.createMultipartConfig();
	}
	
}
