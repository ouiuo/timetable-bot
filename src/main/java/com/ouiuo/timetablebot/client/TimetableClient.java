package com.ouiuo.timetablebot.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TimetableClient {

    private final RestTemplate timetableRestTemplate;

    public void updateByGroupId(UUID groupId) {
        HttpHeaders headers = getHeaders();


        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("groupId", groupId.toString());
        timetableRestTemplate.exchange("/group/add?groupId={groupId}",
                HttpMethod.POST,
                new HttpEntity<>(headers),
                void.class,
                groupId);
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
