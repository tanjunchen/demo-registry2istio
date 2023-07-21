package com.tanjunchen;

import com.tanjunchen.interceptor.RestTemplateHeaderInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @Bean
    //@LoadBalanced
    // 如果需要将 springcloud + http + nacos 接入 istio，则需要去除 LoadBalanced 注解
    public RestTemplate restTemplate() {
        // 注入 Spring RestTemplate Bean，用于实现发送HTTP请求
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        // 添加RestTemplate interceptor
        interceptors.add(new RestTemplateHeaderInterceptor());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }
}
