package com.kk.mall.api.biz.client.auth.identify.mobile;

import cn.hutool.core.util.RandomUtil;
import lombok.Data;


/**
 * @author Wenk.Chan
 * @version 1.0
 * @date 2020/5/28 5:33 下午
 */
@Data
public class MobileQuery {
    @MobileFormat
    private String mobile;

    public String getVerificationCode(){
      return RandomUtil.randomNumbers(6);
    }
}

