package com.kk.mall.api.biz.client.auth.identify;

import cn.hutool.core.lang.ClassScanner;
import com.kk.mall.api.biz.client.auth.identify.operation.IdentifyOperationContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wenk.Chan
 * @version 1.0
 * @date 2020/5/14 3:12 下午
 */
@Component
public class IdentifyTypeProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        int identifyTypeNum = 10;
        Map<String, Class<?>> identifyTypeMap = new HashMap<>(identifyTypeNum);
        ClassScanner.scanPackageByAnnotation("", IdentifyType.class).forEach(clazz -> {
            String type = clazz.getAnnotation(IdentifyType.class).value();
            identifyTypeMap.put(type, clazz);
        });

        IdentifyOperationContext context = new IdentifyOperationContext(identifyTypeMap);
        configurableListableBeanFactory.registerSingleton(IdentifyOperationContext.class.getName(), context);
    }
}


