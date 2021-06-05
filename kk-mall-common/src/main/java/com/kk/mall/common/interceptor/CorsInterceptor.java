package com.kk.mall.common.interceptor;

import org.springframework.core.env.Environment;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Wenk.Chan
 * @version 1.0
 * @date 2020/3/26 3:42 下午
 */

public class CorsInterceptor implements HandlerInterceptor {

    @Resource
    private Environment env;

    private String[] allowDomain = {
            "http://gw-beta.liangyizaixian.com",
            "http://gw.liangyizaixian.com"
    };


    private Set<String> allowOrigins = new HashSet<>(Arrays.asList(allowDomain));

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {

        String origin = request.getHeader("Origin");

        if ("test".equals(env.getActiveProfiles()[0])|| "dev".equals(env.getActiveProfiles()[0])) {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setHeader("Access-Control-Allow-Headers", "Content-type,Authorization,x-requested-with");
            response.setHeader("Access-Control-Expose-Headers", "*");
        }

        if (allowOrigins.contains(origin)) {
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Access-Control-Allow-Origin", origin);
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setHeader("Access-Control-Allow-Headers", "Content-type,Authorization,x-requested-with");
            response.setHeader("Access-Control-Expose-Headers", "*");
        }
        return true;
    }

}

