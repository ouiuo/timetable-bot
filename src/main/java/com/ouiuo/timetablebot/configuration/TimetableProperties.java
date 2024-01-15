package com.ouiuo.timetablebot.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app")
public record TimetableProperties(
        String timetableUrl, int externalSystemReadTimeout
) {

}
