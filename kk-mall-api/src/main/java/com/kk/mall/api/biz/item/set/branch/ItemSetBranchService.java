package com.kk.mall.api.biz.item.set.branch;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.mall.api.biz.item.set.branch.spu.relation.ItemSetBranchSpuRelation;
import com.kk.mall.api.biz.item.set.branch.spu.relation.ItemSetBranchSpuRelationService;
import com.kk.mall.api.enums.ValidEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Wenk.Chan
 */
@Service
public class ItemSetBranchService extends ServiceImpl<ItemSetBranchMapper, ItemSetBranch> implements IService<ItemSetBranch> {


    @Resource
    public ItemSetBranchSpuRelationService itemSetBranchSpuRelationService;

    public List<BranchSmallLogoResp> getItemSetBranchList() {
        List<ItemSetBranch> branchList = getBaseMapper().selectList(Wrappers.<ItemSetBranch>lambdaQuery()
                .eq(ItemSetBranch::getValid, ValidEnum.VALID.getValue())
                .orderByDesc(ItemSetBranch::getSort));

        return branchList.stream().map(branch -> {
            BranchSmallLogoResp branchResp = new BranchSmallLogoResp();
            BeanUtils.copyProperties(branch, branchResp);
            return branchResp;
        }).collect(Collectors.toList());
    }

    public Map<Long, List<BranchMiniLogoVo>> getItemSetBranchListBySpuIds(List<Long> spuIds) {
        //查询spu和品牌关系
        List<ItemSetBranchSpuRelation> relationList = itemSetBranchSpuRelationService
                .list(Wrappers.<ItemSetBranchSpuRelation>lambdaQuery()
                        .in(ItemSetBranchSpuRelation::getSpuId, spuIds));

        Map<Long, List<ItemSetBranchSpuRelation>> spuBranchRelationMap = relationList.stream()
                .collect(Collectors.groupingBy(ItemSetBranchSpuRelation::getSpuId));

        Set<Long> branchIdList = spuBranchRelationMap.values().stream()
                .flatMap(Collection::stream)
                .map(ItemSetBranchSpuRelation::getBranchId)
                .collect(Collectors.toSet());

        //查询品牌列表
        List<ItemSetBranch> branchList = list(Wrappers.<ItemSetBranch>lambdaQuery()
                .eq(ItemSetBranch::getValid, ValidEnum.VALID.getValue())
                .in(ItemSetBranch::getId, branchIdList));

        Map<Long, ItemSetBranch> branchMap = branchList
                .stream().collect(Collectors.toMap(ItemSetBranch::getId, branch -> branch, (branch1, branch2) -> branch1));


        Map<Long, List<BranchMiniLogoVo>> branchMiniLogoMap = new HashMap<>();
        spuBranchRelationMap.forEach((spuId, branchSpuRelationList) -> {
            List<BranchMiniLogoVo> branchMiniLogoVos = branchSpuRelationList.stream().map(itemSetBranchSpuRelation -> {
                BranchMiniLogoVo branchMiniLogoVo = new BranchMiniLogoVo();
                Long branchId = itemSetBranchSpuRelation.getBranchId();
                ItemSetBranch branch = branchMap.get(branchId);
                branchMiniLogoVo.setBranchItemCode(itemSetBranchSpuRelation.getBranchItemCode());
                branchMiniLogoVo.setBranchId(branchId);
                BeanUtils.copyProperties(branch,branchMiniLogoVo);
                return branchMiniLogoVo;
            }).collect(Collectors.toList());
            branchMiniLogoMap.put(spuId, branchMiniLogoVos);

        });
        return branchMiniLogoMap;

    }
}
