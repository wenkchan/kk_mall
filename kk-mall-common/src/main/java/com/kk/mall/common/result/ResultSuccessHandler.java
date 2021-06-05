package com.kk.mall.common.result;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class ResultSuccessHandler implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        OriginalResponse methodAnnotation = returnType.getMethodAnnotation(OriginalResponse.class);
        if (methodAnnotation != null || returnType.getDeclaringClass().getAnnotation(OriginalResponse.class) != null) {
            return body;
        }
        if (body instanceof ApiResult){
            return body;
        }

        Class<?> returnClass = returnType.getMethod().getReturnType();
        if (returnClass.equals(String.class) && body == null) {
            return null;
        }
        String path = serverHttpRequest.getURI().getPath();
        return ApiResult.success(body,path);
    }



}
