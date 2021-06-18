package com.leyou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.leyou.common.vo.PageResult;


import com.leyou.entity.TbBrand;
import com.leyou.pojo.BrandDTO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface TbBrandService extends IService<TbBrand> {

    PageResult<BrandDTO> queryBrandByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc);

    void saveBrandAndCategory(TbBrand brand, List<Long> cids);

    void updateBrandAndCategory(TbBrand brand, List<Long> cids);

    TbBrand findById(Integer id);
}
