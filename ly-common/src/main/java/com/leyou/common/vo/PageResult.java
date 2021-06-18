package com.leyou.common.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {

    private Long total;  //  总条数

    private Long totalPages;  // 总页数

    private List<T> items;   // 返回的数据
}
