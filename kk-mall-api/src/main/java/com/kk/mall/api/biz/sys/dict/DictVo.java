package com.kk.mall.api.biz.sys.dict;

import com.kk.mall.api.biz.sys.dict.item.SysDictItem;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 字典值对象
 */
public class DictVo {
    private String key;
    private String subKey;
    private String value;

    public static List<DictVo> of(SysDict sysDict, List<SysDictItem> sysDictItems) {
        return sysDictItems.stream().map(sysDictItem -> {
            DictVo dictVo = new DictVo();
            dictVo.key = sysDict.getDictKey();
            dictVo.subKey = sysDictItem.getDictKey();
            dictVo.value = sysDictItem.getDictValue();
            return dictVo;
        }).collect(Collectors.toList());
    }

    public String getSubKey() {
        return this.subKey;
    }

    public String stringValue() {
        return this.value;
    }

    public Integer intValue() {
        return Integer.parseInt(this.value);
    }

    public boolean booleanValue() {
        return Boolean.parseBoolean(value);
    }
}
