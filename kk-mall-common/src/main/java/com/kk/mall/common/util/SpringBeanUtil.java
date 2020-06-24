package com.kk.mall.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Wenk.Chan
 * @version 1.0
 * @date 2020/5/14 3:40 下午
 */
@Component
public class SpringBeanUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    //自动初始化注入applicationContext
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        if (applicationContext == null) {
            applicationContext = context;
        }
    }

    /**
     * 通过bean的缩写的方式获取类型
     *
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

}

