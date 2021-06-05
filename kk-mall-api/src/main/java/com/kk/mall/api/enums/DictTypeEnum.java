package com.kk.mall.api.enums;

/**
 * 字典值类型枚举
 */
public enum DictTypeEnum {
    INT(0, "整数"),
    STRING(1, "字符串"),
    BOOLEAN(2, "布尔");
    private Integer value;
    private String text;


    DictTypeEnum(int value, String text) {
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
