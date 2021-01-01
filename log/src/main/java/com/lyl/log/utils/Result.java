package com.lyl.log.utils;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于返回值的工具类
 */
@Data
public class Result {

    //状态
    private Boolean state;
    //消息
    private String msg;
    //数据
    private Object data;

    public static Result success(){
        Result result = new Result();
        result.setState(true);
        result.setMsg("执行成功");
        result.setData(new ArrayList<>());
        return result;
    }

    public static Result success(String msg){
        Result result = new Result();
        result.setState(true);
        result.setMsg(msg);
        result.setData(new ArrayList<>());
        return result;
    }

    public static Result success(Object obj){
        Result result = new Result();
        result.setState(true);
        result.setMsg("执行成功");
        result.setData(obj);
        return result;
    }


    public static Result success(String msg, Object obj){
        Result result = new Result();
        result.setState(true);
        result.setMsg(msg);
        result.setData(obj);
        return result;
    }

    public static Result fail(){
        Result result = new Result();
        Map<String,Object> map = new HashMap<>();
        result.setState(false);
        result.setMsg("执行失败");
        result.setData(new ArrayList<>());
        return result;
    }

    public static Result fail(String msg){
        Result result = new Result();
        result.setState(false);
        result.setMsg(msg);
        result.setData(new ArrayList<>());
        return result;
    }

    public static Result fail(Object obj){
        Result result = new Result();
        Map<String,Object> map = new HashMap<>();
        result.setState(false);
        result.setMsg("执行失败");
        map.put("detail",obj);
        result.setData(map);
        return result;
    }

    public static Result fail(String msg, Object obj){
        Result result = new Result();
        Map<String,Object> map = new HashMap<>();
        result.setState(false);
        result.setMsg(msg);
        map.put("detail",obj);
        result.setData(map);
        return result;
    }


}
