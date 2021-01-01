package com.lyl.log.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class TestServiceTest{

    @Autowired
    private TestService testService;
    @Test
    void getMap() {
        Map<String, Object> map = testService.getMap();
        System.out.println("map = " + map);

    }


    @Test
    public void test3(){

        Map<String,Object> map = new HashMap<>();
        map.put("wait", "void");
        map.put("equals", "boolean");
        map.put("toString", "String");
        map.put("hashCode", "int");
        map.put("getClass", "Class");
        map.put("notify", "void");
        map.put("notifyAll","void");

        Class<? extends TestService> aClass = testService.getClass();
        Method[] methods = aClass.getMethods();
        Field[] fields = aClass.getDeclaredFields();
        System.out.println("成员变量为：");
        for (Field field : fields) {
            System.out.println(field.getName()+" "+field.getType().getSimpleName());
        }
        System.out.println("方法名为");

        List<Method> list = Arrays.asList(methods);

        list.forEach(item->{
            if(!(map.get(item.getName())!=null && map.get(item.getName()).equals(item.getReturnType().getSimpleName()))) {
                System.out.println(item.getName() + " " + item.getReturnType().getSimpleName());
            }
        });
        System.out.println("第二种方法");
        list.forEach(item->{
            if(map.get(item.getName())==null){
                System.out.println(item.getName() + " " + item.getReturnType().getSimpleName());
            }
        });

    }
}