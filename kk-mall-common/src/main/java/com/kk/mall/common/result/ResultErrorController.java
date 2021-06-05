package com.kk.mall.common.result;

import com.google.common.collect.ImmutableMap;
import com.kk.mall.common.exception.CommonErrno;
import com.kk.mall.common.exception.Errno;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.Resource;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;

/**
 * @author chenwenkun
 */
public class ResultErrorController implements ErrorController {

    private static final String PATH = "/error";

    @Resource
    private ErrorAttributes errorAttributes;

    public ResultErrorController() {

    }


    @RequestMapping(value = PATH)
    public ResponseEntity<?> handleError(WebRequest webRequest) {
        Map<String, Object> errorAttributes = getErrorAttributes(webRequest);
        String path = (String) errorAttributes.get("path");
        int status = (int) errorAttributes.get("status");
        String message = (String) errorAttributes.get("message");


        ApiResult<ImmutableMap<String, String>> apiResult =
                ApiResult.error(
                         CommonErrno.SYSTEM_ERROR, path, of("info", message));


        return new ResponseEntity<>(apiResult, new HttpHeaders(), HttpStatus.valueOf(status));
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    private Map<String, Object> getErrorAttributes(WebRequest webRequest) {
        return errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());
    }


}
