package com.tanjunchen.util;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Header工具类
 */
public class HttpHeaderUtil {
    /**
     * 获取Trace相关header
     * @return
     */
    public static Map<String, String> getTraceHeaderMap() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String> traceHeaderMap = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String headerKey = enumeration.nextElement();
            // b3 propagation(透传x-b3-开头的header)
            if (!StringUtils.isEmpty(headerKey) && headerKey.toLowerCase().contains("x-b3-")) {
                String headerValue = request.getHeader(headerKey);
                traceHeaderMap.put(headerKey, headerValue);
            }
        }
        return traceHeaderMap;
    }
}