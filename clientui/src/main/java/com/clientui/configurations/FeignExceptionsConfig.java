package com.clientui.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.clientui.exceptions.CustomErrorDecoder;

@Configuration
public class FeignExceptionsConfig {
	
	@Bean
	public CustomErrorDecoder customErrorDecoder() {
		return new CustomErrorDecoder();
	}

}
