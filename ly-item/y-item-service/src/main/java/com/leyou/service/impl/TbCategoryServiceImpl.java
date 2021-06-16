package com.leyou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.BeanHelper;
import com.leyou.entity.TbCategory;
import com.leyou.mapper.TbCategoryMapper;
import com.leyou.pojo.CategoryDTO;
import com.leyou.service.TbCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class TbCategoryServiceImpl extends ServiceImpl<TbCategoryMapper,
        TbCategory> implements TbCategoryService {


    @Override
    public List<CategoryDTO> queryTbCategoryListByPid(Long pid) {
        QueryWrapper<TbCategory> queryWrapper=new QueryWrapper<>();
       // queryWrapper.eq("parentId",pid);
        queryWrapper.lambda().eq(TbCategory::getParentId,pid);
     //queryWrapper.lambda().eq(t ->t.getParentId(),pid);
        List<TbCategory> tbCategoryList = this.baseMapper.selectList(queryWrapper);

        if(CollectionUtils.isEmpty(tbCategoryList)){
            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        List<CategoryDTO> categoryDTOS = BeanHelper.copyWithCollection(tbCategoryList, CategoryDTO.class);

        return categoryDTOS;
    }
}
