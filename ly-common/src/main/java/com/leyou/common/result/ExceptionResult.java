package com.leyou.common.result;

import com.leyou.common.exception.LyException;
import lombok.Data;
import org.joda.time.DateTime;

@Data
public class ExceptionResult {
    private Integer status;
    private String message;
    private String timetamp;
    public ExceptionResult(LyException e){
        this.status=e.getStatus();
        this.message=e.getMessage();
        this.timetamp= DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
    }
}
