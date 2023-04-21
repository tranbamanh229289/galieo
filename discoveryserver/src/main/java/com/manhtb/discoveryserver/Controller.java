package com.manhtb.discoveryserver;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {
    public void get() {
        RestTemplate template = new RestTemplate();
    }
}
