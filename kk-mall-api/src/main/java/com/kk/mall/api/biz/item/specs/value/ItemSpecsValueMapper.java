package com.kk.mall.api.biz.item.specs.value;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kk.mall.api.biz.item.specs.SpecsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenwenkun
 */
@Mapper
public interface ItemSpecsValueMapper extends BaseMapper<ItemSpecsValue> {

    List<SpecsVo> getSpecsList(@Param("itemProductIdList") List<Long> itemProductIdList);
}
