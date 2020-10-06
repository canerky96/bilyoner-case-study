package com.bilyoner.assignment.couponapi.config;

import javax.annotation.PostConstruct;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;


import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class MessageSourceConfig {

	private final MessageTextResolver messageTextResolver;

	@PostConstruct
	public void init() {
		messageTextResolver.registerMessageSource(messageSource());
	}

    @Bean
    public MessageSource messageSource() {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/messages/coupon-api-messages");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(5);
        return messageSource;
    }


}
