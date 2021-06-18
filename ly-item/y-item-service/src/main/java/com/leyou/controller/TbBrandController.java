package com.leyou.controller;

import com.leyou.common.vo.PageResult;

import com.leyou.entity.TbBrand;
import com.leyou.pojo.BrandDTO;
import com.leyou.service.TbBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("brand")
@RestController
public class TbBrandController {

    @Autowired
    private TbBrandService tbBrandService;

    @GetMapping("page")
    public ResponseEntity<PageResult<BrandDTO>> queryBrandByPage(
            @RequestParam(value = "key", required = false)String key,
            @RequestParam(value = "page", defaultValue = "1")Integer page,
            @RequestParam(value = "rows", defaultValue = "5")Integer rows,
            @RequestParam(value = "sortBy", required = false)String sortBy,
            @RequestParam(value = "desc", required = false)Boolean desc
    ){
        return ResponseEntity.ok(tbBrandService.queryBrandByPage(key,page,rows,sortBy,desc));
    }

    @PostMapping
    public ResponseEntity<Void> save(TbBrand brand, @RequestParam(value = "cids")List<Long> cids){
        tbBrandService.saveBrandAndCategory(brand, cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(TbBrand brand,@RequestParam(name = "cids") List<Long> cids){
        tbBrandService.updateBrandAndCategory(brand,cids);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
