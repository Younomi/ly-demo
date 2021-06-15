package com.leyou;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestControllrt {
    @GetMapping("/test")
    public String add(Integer price){
        if(null==price){
            throw new LyException(ExceptionEnum.INVALID_PARAM_ERROR);
        }
        return "";
    }

}
