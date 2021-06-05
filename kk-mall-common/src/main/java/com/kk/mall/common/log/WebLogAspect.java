package com.kk.mall.common.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.mall.common.Const;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

import static ch.qos.logback.core.CoreConstants.LINE_SEPARATOR;

@Slf4j
@Aspect
@Component
@Order(2)
//@Profile({"dev", "test"})
public class WebLogAspect {

    @Pointcut(
            "@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
                    "@annotation(org.springframework.web.bind.annotation.PostMapping) ||" +
                    "@annotation(org.springframework.web.bind.annotation.PutMapping) ||" +
                    "@annotation(org.springframework.web.bind.annotation.DeleteMapping)"
    )
    public void webLog() {
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        if (result != null) {
            String resultStr = new ObjectMapper().writeValueAsString(result);
            String[] lines = JsonLogFormatter.format("Response Args   : ", resultStr);

            for (String line : lines) {
                log.info(line);
            }

        }
        log.info("Time Consuming : {} ms", System.currentTimeMillis() - startTime);
        return result;
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        MethodSignature sign = (MethodSignature) joinPoint.getSignature();
        Method method = sign.getMethod();
        ApiOperation annotation = method.getAnnotation(ApiOperation.class);
        if (annotation != null) {
            String apiName = "[" + annotation.value() + "] ";
            MDC.put(Const.API_NAME, apiName);
        }


        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        log.info("========================================== Start ==========================================");
        log.info("URL            : {}", request.getRequestURL().toString());
        log.info("HTTP Method    : {}", request.getMethod());
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        if (joinPoint.getArgs().length>0){
            String requestArgStr = new ObjectMapper().writeValueAsString(joinPoint.getArgs()[0]);
            String[] lines = JsonLogFormatter.format("Request Args   : ", requestArgStr);

            for (String line : lines) {
                log.info(line);
            }
        }

    }

    @After("webLog()")
    public void doAfter() {
        log.info("=========================================== End ===========================================" + LINE_SEPARATOR);
    }
}
