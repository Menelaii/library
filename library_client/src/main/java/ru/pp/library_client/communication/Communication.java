package ru.pp.library_client.communication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public abstract class Communication {

    @Value("${backend.url}")
    protected String ServiceUrl;
    protected RestTemplate RestTemplate;

    public Communication(RestTemplate restTemplate) {
        this.RestTemplate = restTemplate;
    }
}
