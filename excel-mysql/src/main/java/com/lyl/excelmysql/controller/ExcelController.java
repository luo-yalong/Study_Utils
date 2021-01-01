package com.lyl.excelmysql.controller;

import com.lyl.excelmysql.dao.EmpDao;
import com.lyl.excelmysql.pojo.Emp;
import com.lyl.excelmysql.utils.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
public class ExcelController {
    @Autowired
    private EmpDao empDao;

    @PostMapping("/uploadExcel")
    public String uploadExcel(@RequestParam("file") MultipartFile file) {
        //获取文件名
        String name = file.getOriginalFilename();

        if(name.length()<6 || !name.substring(name.length()-5).equals(".xlsx")){
            return "文件格式错误";
        }

        List<Emp> emps = null;
        try {
            emps = ExcelUtils.excelToList(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("导入的数据为：");
        for (Emp emp : emps) {
            System.out.println(emp);
        }
        return "保存成功";
    }

    @PostMapping("/export")
    public void export() throws IOException {
        List<Emp> emps = empDao.selectAll();
        for (Emp emp : emps) {
            System.out.println(emp);
        }

        /**
         * workBook有不同的实现类，HSSFWorkbook可以创建以xls结尾的文件
         *  而XSSFWorkbook可以创建以xlsx结尾的文件
         */
        Workbook workbook = new HSSFWorkbook();

        Sheet sheet = workbook.createSheet("员工表数据");

        Row row;//创建第一个单元格
//        row = sheet.createRow(0);
//        row.setHeight((short) (26.25*20));
//        row.createCell(0).setCellValue("员工信息列表");//为第一行单元格设置值

        /**
         * 为标题设计空间
         */
/*        CellRangeAddress rowRegion = new CellRangeAddress(0,0, 0, 3);
        sheet.addMergedRegion(rowRegion);*/

        row = sheet.createRow(0);
        row.setHeight((short) (22.50*20));//设置行高
        row.createCell(0).setCellValue("empid");
        row.createCell(1).setCellValue("empname");
        row.createCell(2).setCellValue("empage");
        row.createCell(3).setCellValue("empdept");

        int n = 1;
        for (Emp emp : emps) {
            row = sheet.createRow(n++);
            row.createCell(0).setCellValue(emp.getEmpid());
            row.createCell(1).setCellValue(emp.getEmpname());
            row.createCell(2).setCellValue(emp.getEmpage());
            row.createCell(3).setCellValue(emp.getEmpdept());
        }
//        sheet.setDefaultRowHeight((short) (16.50*20));
//        //列宽自适应
//        for (int i = 0; i <= 13; i++) {
//            sheet.autoSizeColumn(i);
//        }
//        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        File file = new File("D:/emp.xls");
        OutputStream os = new FileOutputStream(file);
        workbook.write(os);
        os.flush();
        os.close();
    }


    @PostMapping("/export1")
    public void export1() throws IOException {
        List<Emp> emps = empDao.selectAll();
        for (Emp emp : emps) {
            System.out.println(emp);
        }

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("员工表数据");

        Row row;//创建第一个单元格


        row = sheet.createRow(0);
        row.setHeight((short) (22.50*20));//设置行高
        row.createCell(0).setCellValue("empid");
        row.createCell(1).setCellValue("empname");
        row.createCell(2).setCellValue("empage");
        row.createCell(3).setCellValue("empdept");

        int n = 1;
        for (Emp emp : emps) {
            row = sheet.createRow(n++);
            row.createCell(0).setCellValue(emp.getEmpid());
            row.createCell(1).setCellValue(emp.getEmpname());
            row.createCell(2).setCellValue(emp.getEmpage());
            row.createCell(3).setCellValue(emp.getEmpdept());
        }

        File file = new File("D:/emp.xlsx");
//        OutputStream os = new FileOutputStream(file);
        OutputStream os = new FileOutputStream("D:/emp.xlsx");
        workbook.write(os);
        os.flush();
        os.close();
    }


    /**
     * 通过数据库名和表名
     * @param table_shema
     * @param table_name
     * @throws IOException
     */
    @PostMapping("/export2")
    public void export2(String table_shema,String table_name) throws IOException {
        //获取数据表中对应的字段
        List<String> allColumn = empDao.getAllColumn(table_shema, table_name);
        List<Emp> emps = empDao.selectAll();
        for (Emp emp : emps) {
            System.out.println(emp);
        }

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("员工表数据");

        Row row;//创建第一个单元格

        row = sheet.createRow(0);
        for (int i = 0; i < allColumn.size(); i++) {
            row.createCell(i).setCellValue(allColumn.get(i));
        }

        int n = 1;
        for (Emp emp : emps) {
            row = sheet.createRow(n++);
            row.createCell(0).setCellValue(emp.getEmpid());
            row.createCell(1).setCellValue(emp.getEmpname());
            row.createCell(2).setCellValue(emp.getEmpage());
            row.createCell(3).setCellValue(emp.getEmpdept());
        }

        OutputStream os = new FileOutputStream("D:/emp.xlsx");
        workbook.write(os);
        os.flush();
        os.close();
    }

    @PostMapping("/export3")
    public void export3(String table_shema,String table_name) throws IOException {
        //获取数据表中对应的字段
        List<String> allColumn = empDao.getAllColumn(table_shema, table_name);
        List<Emp> emps = empDao.selectAll();
        for (Emp emp : emps) {
            System.out.println(emp);
        }

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("员工表数据");

        Row row;//创建第一个单元格

        int m = 0;
        row = sheet.createRow(m++);
        for (int i = 0; i < allColumn.size(); i++) {
            row.createCell(i).setCellValue(allColumn.get(i));
        }

        for (Emp emp : emps) {
            row = sheet.createRow(m++);
            for (int i = 0; i < allColumn.size(); i++) {
                row.createCell(i).setCellValue(String.valueOf(getMethodName(emp,allColumn.get(i))));
            }
        }

        OutputStream os = new FileOutputStream("D:/emp.xlsx");
        workbook.write(os);
        os.flush();
        os.close();
    }


    /**
     * 通过对象和属性名来执行对应的get方法
     * @param obj
     * @param name
     * @return
     */
    public Object getMethodName (Object obj, String name){
        name = "get"+name.substring(0,1).toUpperCase() + name.substring(1);
        Object invoke = null;
        try {
            invoke = obj.getClass().getMethod(name, null).invoke(obj, null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return invoke;
    }
}
