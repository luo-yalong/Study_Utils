package com.lyl.log.config;

import com.lyl.log.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 *
 */
@Slf4j
@RestControllerAdvice(annotations = {RestController.class})
public class MyRestExceptionHandler {

    private Integer i=1;
    private List<Map<String,Object>> list = new ArrayList<>();

    @ExceptionHandler(Exception.class)
    public Result runtimeExceptionHandler(Exception e, HttpServletRequest request){
        /*
            如果端口号为本地，即启用debug模式，否则，启动错误信息简写模式。
            同时在
         */
        StackTraceElement[] stackTrace = e.getStackTrace();
        String methodName = stackTrace[0].getMethodName();//方法名
        Integer lineNumber = stackTrace[0].getLineNumber();//出错的行
        String className = stackTrace[0].getClassName();
        getParamsOfMethod(methodName, className);

        //错误信息的简写
        String errorMsg = e.getMessage() == null ? "空指针异常" : e.getMessage();
        //请求类型
        String method = request.getMethod();
        //请求
        String uri = request.getRequestURI();

        //获取请求参数,
        Map<String,Object> paramMap = new HashMap<>();
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()){
            String key = names.nextElement();//参数名
            String value = request.getParameter(key);//参数值
            paramMap.put(key,value);
        }

        //请求参数
        String params = paramMap.toString();
        params = params.substring(1,params.length()-1).replaceAll(", ","&");
        //完整请求
        String completeUrl = request.getRequestURL() + (StringUtils.hasLength(params) ? "?"+params : "");

        Integer port = request.getServerPort();
        Map<String, Object> map = getParamsOfMethod(methodName, className);

        log.error("异常原因: {}",errorMsg);
        log.error("端口号: {}",port);
        log.error("请求类型: {} , 请求: {}",method,uri);
        if("localhost".equals(request.getServerName())){
            log.error("请求参数: {} , 参数个数: {}",paramMap.toString(),paramMap.size());
        }
/*        if(!"localhost".equals(request.getServerName()))
            log.info("请求参数: {} , 参数个数: {}",paramMap.toString(),paramMap.size());*/
        log.error("完整请求: {}",completeUrl);
        log.error("方法名: {} , 形参列表: {} , 形参个数: {}",methodName,map,map.size());
        log.error("类名: {}",className);
        log.error("出错的行: {}",lineNumber);
        log.error("详细信息: ",e);


        Map<String,Object> map2= new HashMap<>();
        map2.put("异常原因",errorMsg);
        map2.put("端口号",port);
        map2.put("请求类型", method);
        map2.put("请求", uri);
        map2.put("完整请求",completeUrl);
        list.add(map2);
        if(e instanceof MyException){
            for (Map<String, Object> stringObjectMap : list) {
                System.out.println(stringObjectMap);
            }
            i = 1;
            list = new ArrayList<>();
        }else if(i++ != 5){
            System.out.println("异常处理中i = " + i);
        }else{
            for (Map<String, Object> stringObjectMap : list) {
                System.out.println(stringObjectMap);
            }
            i=1;
            list = new ArrayList<>();
        }

        return Result.fail("执行失败");
    }

    /**
     * 通过类名和方法名获取参数列表和类型
     * @param methodName
     * @param className
     * @return
     */
    private Map<String,Object> getParamsOfMethod(String methodName, String className) {
        Map<String,Object> map = new HashMap<>();
        try {
//            System.out.println("className:"+className);
            Class<?> aClass = Class.forName(className);
//            System.out.println("参数列表：");
            Method[] declaredMethods = aClass.getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                if(methodName.equals(declaredMethod.getName())){
                    Parameter[] parameters = declaredMethod.getParameters();
                    for (Parameter parameter : parameters) {
                        String name = parameter.getType().getName();
                        map.put(parameter.getName(),name.substring(name.lastIndexOf(".")+1));
                    }
                }
            }

        } catch (Exception e) {
            return null;
        }
        return map;
    }


}
