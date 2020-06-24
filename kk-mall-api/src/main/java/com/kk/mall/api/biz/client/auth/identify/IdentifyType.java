package com.kk.mall.api.biz.client.auth.identify;

import java.lang.annotation.*;

/**
 * @author Wenk.Chan
 * @version 1.0
 * @date 2020/5/12 9:23 上午
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface IdentifyType {
    String value();
}

