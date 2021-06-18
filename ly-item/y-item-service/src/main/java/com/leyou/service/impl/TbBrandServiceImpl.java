package com.leyou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.BeanHelper;
import com.leyou.common.vo.PageResult;

import com.leyou.entity.TbBrand;
import com.leyou.entity.TbCategoryBrand;
import com.leyou.mapper.TbBrandMapper;
import com.leyou.pojo.BrandDTO;
import com.leyou.service.TbBrandService;
import com.leyou.service.TbCategoryBrandService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbBrandServiceImpl extends ServiceImpl<TbBrandMapper, TbBrand> implements TbBrandService {

    @Autowired
    private TbCategoryBrandService categoryBrandService;

    @Override
    public PageResult<BrandDTO> queryBrandByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        //page是当前页，rows为展示条数
        Page<TbBrand> tbBrandPage = new Page<>(page, rows);
        QueryWrapper<TbBrand> tbBrandQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(key)) {
            //key为模糊查询 根据名称和品牌首字母查询
            tbBrandQueryWrapper.lambda().like(TbBrand::getName, key).or().like(TbBrand::getLetter, key);
        }
        //排序条件
        if (StringUtils.isNotEmpty(sortBy)) {
            //点击进行排序时传true
            if (desc){
                tbBrandPage.setDesc(sortBy);
            } else {
                tbBrandPage.setAsc(sortBy);
            }
        }
        //将page条件填入QueryWrapper 的判断条件中
        IPage<TbBrand> brandIPage = this.page(tbBrandPage);
        //综合所有分页条件如果为空就代表没有查询出来 返回报错信息
        if (null == tbBrandPage || CollectionUtils.isEmpty(brandIPage.getRecords())){
            throw new LyException(ExceptionEnum.BRAND_NOT_FOUND);
        }
        List<BrandDTO> brandDTOList = BeanHelper.copyWithCollection(brandIPage.getRecords(), BrandDTO.class);


        return new PageResult<BrandDTO>(brandIPage.getTotal(), brandIPage.getPages(), brandDTOList);
    }

    @Override
    @Transactional
    public void saveBrandAndCategory(TbBrand brand, List<Long> cids) {
        //保存品牌表
        boolean brandB = this.save(brand);
        if(!brandB){
            throw  new LyException(ExceptionEnum.INSERT_OPERATION_FAIL);
        }
        Long brandId = brand.getId();
        if(!CollectionUtils.isEmpty(cids)){
            List list = new ArrayList<TbCategoryBrand>();
            for (Long cid : cids) {
                TbCategoryBrand tbCategoryBrand = new TbCategoryBrand();
                tbCategoryBrand.setBrandId(brandId);
                tbCategoryBrand.setCategoryId(cid);
                list.add(tbCategoryBrand);
            }
            //批量保存 分类品牌 中间表
            categoryBrandService.saveBatch(list);
        }
    }

    @Override
    @Transactional
    public void updateBrandAndCategory(TbBrand brand, List<Long> cids) {
        boolean brandB = this.updateById(brand);
        if(!brandB){
            throw  new LyException(ExceptionEnum.UPDATE_OPERATION_FAIL);
        }
        Long brandId = brand.getId();
        if(!CollectionUtils.isEmpty(cids)){
            //先删除,中间表
            QueryWrapper<TbCategoryBrand> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(TbCategoryBrand::getBrandId,brandId);
            boolean removeB = categoryBrandService.remove(queryWrapper);
            if(!removeB){
                throw new LyException(ExceptionEnum.DELETE_OPERATION_FAIL);
            }
            //新增 中间表
            List list = new ArrayList<TbCategoryBrand>();
            for (Long cid : cids) {
                TbCategoryBrand tbCategoryBrand = new TbCategoryBrand();
                tbCategoryBrand.setBrandId(brandId);
                tbCategoryBrand.setCategoryId(cid);
                list.add(tbCategoryBrand);
            }
            categoryBrandService.saveBatch(list);
        }
    }

    @Override
    public TbBrand findById(Integer id) {
        TbBrand byId = this.getById(id);
        return byId;
    }
}
