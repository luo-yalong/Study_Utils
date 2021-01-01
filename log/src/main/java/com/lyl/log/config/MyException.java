package com.lyl.log.config;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MyException extends Exception{

    private Boolean state;
    private Boolean isShow;
    private String msg;

}
