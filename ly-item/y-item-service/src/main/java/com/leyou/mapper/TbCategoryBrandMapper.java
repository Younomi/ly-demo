package com.leyou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leyou.entity.TbCategoryBrand;


/**
 * <p>
 * 商品分类和品牌的中间表，两者是多对多关系 Mapper 接口
 * </p>
 *
 * @author HM
 * @since 2019-12-23
 */
public interface TbCategoryBrandMapper extends BaseMapper<TbCategoryBrand> {

}
