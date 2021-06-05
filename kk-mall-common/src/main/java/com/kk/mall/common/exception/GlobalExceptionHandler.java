package com.kk.mall.common.exception;

import com.google.common.collect.ImmutableMap;
import com.kk.mall.common.Const;
import com.kk.mall.common.result.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.collect.ImmutableMap.of;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BizException.class)
    @ResponseBody
    public ResponseEntity<?> handleBizException(BizException ex, HttpServletRequest request) {
        String path = request.getRequestURI();
        Map<String, Object> dataMap = Optional.ofNullable(ex.getData()).orElse(new HashMap<>());
        ApiResult<Object> apiResult = ApiResult.error(ex.getError(), path, dataMap.size() > 0 ? dataMap : null);
        log.error("[biz error]", ex);
        return new ResponseEntity<>(apiResult, new HttpHeaders(), HttpStatus.OK);
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResponseEntity<?> handleInvalidRequest(BindException ex, HttpServletRequest request) {
        String path = request.getRequestURI();

        Map<String, Object> error = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, fieldError -> {
                    String message = fieldError.getDefaultMessage();
                    return StringUtils.isEmpty(message) ? "no hint" : message;
                }));
        ApiResult<Object> apiResult = ApiResult.error(CommonErrno.REQUEST_PARAMS_VALIDATION_FAILED, path, error
                , ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage());


        log.error("[request param error] {}", error);
        return new ResponseEntity<>(apiResult, new HttpHeaders(), HttpStatus.OK);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<?> handleInvalidRequest(ConstraintViolationException ex, HttpServletRequest request) {
        String path = request.getRequestURI();

        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        Map<String, Object> error = constraintViolations.stream().collect(Collectors.toMap(constraintViolation -> {
            String showMessage = constraintViolation.getPropertyPath().toString();
            return showMessage.substring(showMessage.indexOf(".") + 1);

        }, ConstraintViolation::getMessage));

        String showMessage = constraintViolations.iterator().next().getPropertyPath().toString();
        showMessage = showMessage.substring(showMessage.indexOf(".") + 1);

        ApiResult<Object> apiResult = ApiResult.error(CommonErrno.REQUEST_PARAMS_VALIDATION_FAILED, path, error,showMessage);

        log.error("[request param error] {}", error);
        return new ResponseEntity<>(apiResult, new HttpHeaders(), HttpStatus.OK);
    }


    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseEntity<?> handleGeneralException(Throwable ex, HttpServletRequest request) {
        String path = request.getRequestURI();

        ApiResult<ImmutableMap<String, String>> apiResult = ApiResult.error(CommonErrno.SYSTEM_ERROR, path, of("detail", ex.toString()));
        log.error("[system error]", ex);

        return new ResponseEntity<>(apiResult, new HttpHeaders(), HttpStatus.OK);
    }


}
