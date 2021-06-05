package com.kk.mall.api.enums;

/**
 * 有效性枚举
 */
public enum ValidEnum {
    VALID(1, "有效"),
    INVALID(0, "无效");
    private Integer value;
    private String text;


    ValidEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }
    public Integer getValue() {
        return this.value;
    }

    public String getText() {
        return this.text;
    }
}
