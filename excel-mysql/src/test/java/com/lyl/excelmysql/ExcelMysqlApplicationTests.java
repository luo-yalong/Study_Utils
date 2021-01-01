package com.lyl.excelmysql;

import com.lyl.excelmysql.dao.EmpDao;
import com.lyl.excelmysql.pojo.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ExcelMysqlApplicationTests {

    @Test
    void contextLoads() {
        Emp emp = new Emp();
        Field[] fields = emp.getClass().getDeclaredFields();
        String typeName = emp.getClass().getTypeName();
        System.out.println("typeName="+typeName);

        List<String> varNames = new ArrayList<>();

        for (Field field : fields) {
            //对于每一个属性，获取属性名
            String varName = field.getName();
            varNames.add(varName);
            System.out.println("private "+field.getType().getSimpleName()+" "+ varName);
        }
        System.out.println("成员变量的个数："+fields.length);

        Method[] methods = emp.getClass().getMethods();
        for (Method method : methods) {
            String name = method.getName();
            if("get".equals(name.substring(0,3)) && !"getClass".equals(name)){
                String returnType = method.getReturnType().getSimpleName();
                int i = method.getParameterCount();
                System.out.println("方法名："+name + "返回值类型："+returnType + " 形参个数：" + i );
            }
        }


        System.out.println("********************88");
        List<String> getMethodName = getGetMethodName(varNames);
        for (String varName : getMethodName) {
            System.out.println(varName);
        }

    }

    @Autowired
    private EmpDao empDao;
    @Test
    public void test1(){
        List<String> allColumn = empDao.getAllColumn("test", "emp");
        System.out.println("字段的数量为："+allColumn.size());
        for (String s : allColumn) {
            System.out.println(s);
        }
    }

    /**
     * 拼接对应属性名的get方法
     * @param list
     */
    public List<String> getGetMethodName(List<String> list){
        List<String> l = new ArrayList();
        for (String s : list) {
            s = "get"+s.substring(0,1).toUpperCase()+s.substring(1);
            l.add(s);
        }
        return l;
    }


    /**
     * 通过属性名获取执行对应的方法
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    public void test2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Emp emp = new Emp();
        emp.setEmpid(1);
        emp.setEmpname("张三");
        emp.setEmpage(12);
        emp.setEmpdept(2);

        Class<? extends Emp> aClass = emp.getClass();
        Field[] fields = aClass.getDeclaredFields();

        List<String> varNames = new ArrayList<>();
        for (Field field : fields) {
            //对于每一个属性，获取属性名
            String varName = field.getName();
            varNames.add(varName);
            System.out.println("private "+field.getType().getSimpleName()+" "+ varName);
        }
        List<String> getMethodName = getGetMethodName(varNames);
        for (String s : getMethodName) {
            Method method = aClass.getMethod(s, null);
            Object invoke = method.invoke(emp, null);
            System.out.println(s+" :==>"+invoke);

        }


    }

    @Test
    public void test3() throws NoSuchMethodException {

        String str = "123.txt";
        boolean txt = str.endsWith(".txt");
        System.out.println(txt);

    }

    @Test
    public void test4(){
        Emp emp = new Emp();

    }



}
