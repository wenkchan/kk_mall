package com.kk.mall.api.biz.client.auth.identify.mobile;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Wenk.Chan
 * @version 1.0
 * @date 2020/5/27 3:27 下午
 */
public class MobileFormatVerify implements ConstraintValidator<MobileFormat,String> {
    @Override
    public void initialize(MobileFormat constraintAnnotation) {

    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String regex="^(1[3-9])\\d{9}$";
        if(StringUtils.isNotBlank(value)){
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(value);
            return m.matches();
        }
        return false;
    }
}

