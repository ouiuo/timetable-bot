package com.ouiuo.timetablebot.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RootUriTemplateHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Configuration
public class RestConfig {

    private final TimetableProperties properties;

    @Bean
    public RestTemplate timetableRestTemplate() {
        RestTemplate restTemplate = new RestTemplate(requestFactory());
        RootUriTemplateHandler handler = new RootUriTemplateHandler(properties.timetableUrl(), restTemplate.getUriTemplateHandler());
        restTemplate.setUriTemplateHandler(handler);

        return restTemplate;
    }

    @Bean
    public ClientHttpRequestFactory requestFactory() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(properties.externalSystemReadTimeout());

        return requestFactory;
    }
}
