package com.leyou.controller;

import com.leyou.entity.TbCategory;
import com.leyou.pojo.CategoryDTO;
import com.leyou.service.TbCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class TbCategoryController {

    @Autowired
    private TbCategoryService tbCategoryService;
      @GetMapping("/of/parent")
      public ResponseEntity<List<CategoryDTO>> queryCategoryByPid(@RequestParam(value = "pid")Long pid){
          List<CategoryDTO> categoryDTOS = tbCategoryService.queryTbCategoryListByPid(pid);

          return  ResponseEntity.ok(categoryDTOS);
      }
}
