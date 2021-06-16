package com.leyou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.leyou.entity.TbCategory;
import com.leyou.pojo.CategoryDTO;

import java.util.List;

public interface TbCategoryService extends IService<TbCategory> {
    public List<CategoryDTO> queryTbCategoryListByPid(Long pid);

}
