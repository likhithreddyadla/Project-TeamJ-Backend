package com.nutri.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class NutriEatsApplication extends SpringBootServletInitializer {


	@Value("${cors.prodCrossOrigin}")
	private String prodCrossOrigin;

	@Value("${cors.localhostOrigin}")
	private String localhostOrigin;

	public static void main(String[] args) {
		SpringApplication.run(NutriEatsApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(NutriEatsApplication.class);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				if(localhostOrigin==null || localhostOrigin.isEmpty()) {
					registry.addMapping("/**").allowedOrigins(
							prodCrossOrigin);
				}
				else {
					registry.addMapping("/**").allowedOrigins(
							prodCrossOrigin,
							localhostOrigin);
				}
			}
		};
	}

}
