package com.tanjunchen.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class EchoController {

    @Resource
    private RestTemplate restTemplate;

    // public static final String INVOKE_URL = "http://provider-demo";
    // 如果需要将 springcloud + http + nocas 接入 istio，则需要适配 serviceentry 中的端口
    public static final String INVOKE_URL = "http://provider-demo";

    private static final Logger logger = LoggerFactory.getLogger(EchoController.class);

    @RequestMapping(value = "/echo-rest/{message}", method = RequestMethod.GET)
    public String rest(@PathVariable String message) {
        logger.info("consumer access: /echo-rest/" + message);
        return restTemplate.getForObject(INVOKE_URL+"/echo/"+message, String.class);
    }
}