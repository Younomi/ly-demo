package com.leyou.controller;

import com.leyou.entity.TbBrand;
import com.leyou.entity.TbCategory;
import com.leyou.pojo.CategoryDTO;
import com.leyou.service.TbBrandService;
import com.leyou.service.TbCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/category")
public class TbCategoryController {

    @Autowired
    private TbCategoryService tbCategoryService;
    @Autowired
    private TbBrandService tbBrandService;
      @GetMapping("/of/parent")
      public ResponseEntity<List<CategoryDTO>> queryCategoryByPid(@RequestParam(value = "pid")Long pid, HttpServletResponse response){
          List<CategoryDTO> categoryDTOS = tbCategoryService.queryTbCategoryListByPid(pid);
          return  ResponseEntity.ok(categoryDTOS);
      }
    @GetMapping("/of/brand/findById")
    public ResponseEntity<TbBrand> findById(@RequestParam("id")Integer id){
        return ResponseEntity.ok(tbBrandService.findById(id));
    }
}
