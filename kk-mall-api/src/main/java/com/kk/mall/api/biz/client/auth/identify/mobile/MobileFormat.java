package com.kk.mall.api.biz.client.auth.identify.mobile;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @author Wenk.Chan
 * @version 1.0
 * @date 2020/5/27 3:27 下午
 */
@Constraint(validatedBy = MobileFormatVerify.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MobileFormat {

    String message() default "手机号码不正确";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

