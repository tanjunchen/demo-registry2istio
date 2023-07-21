package com.tanjunchen.interceptor;

import com.tanjunchen.util.HttpHeaderUtil;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Map;

/**
 * 实现微服务相关Trace透传
 */
public class RestTemplateHeaderInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] body,
                                        ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        Map<String, String> traceHeaderMap = HttpHeaderUtil.getTraceHeaderMap();
        if (!CollectionUtils.isEmpty(traceHeaderMap)) {
            for (Map.Entry<String, String> headerEntry : traceHeaderMap.entrySet()) {
                // 透传微服务Trace相关header
                httpRequest.getHeaders().add(headerEntry.getKey(),  headerEntry.getValue());
            }
        }
        // 执行正常请求
        ClientHttpResponse response = clientHttpRequestExecution.execute(httpRequest, body);
        return response;
    }

}
