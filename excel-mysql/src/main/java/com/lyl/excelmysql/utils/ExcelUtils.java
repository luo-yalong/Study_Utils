package com.lyl.excelmysql.utils;

import com.lyl.excelmysql.pojo.Emp;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    public static List<Emp> excelToList(InputStream inputStream){
        List<Emp> list = new ArrayList<>();
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(inputStream);
            //关闭流
            inputStream.close();
            //工作表对象
            Sheet sheet = workbook.getSheetAt(0);
            //总行数
            int rowNum = sheet.getLastRowNum();
            //工作表的列
            Row row = sheet.getRow(0);
            //总列数
            int cellNum = row.getLastCellNum();

            //得到指定的单元格
            Cell cell = row.getCell(0);
            //从i=1开始，i=0为表头
            for (int i = 1; i <= rowNum; i++) {
                Emp emp = new Emp();
                row = sheet.getRow(i);

                for (int j = 0; j < cellNum; j++) {
                    //列 ： 0 : empid  1 : empname    2: empage   3: empdept
                    cell = row.getCell(j);
                    if(cell != null){
                        //设置单元格中数据的类型
                        cell.setCellType(CellType.STRING);
                        String data = cell.getStringCellValue().trim();
                        switch (j){
                            case 0:
                                emp.setEmpid(Integer.valueOf(data));
                                break;
                            case 1:
                                emp.setEmpname(data);
                                break;
                            case 2:
                                emp.setEmpage(Integer.valueOf(data));
                                break;
                            case 3:
                                emp.setEmpdept(Integer.valueOf(data));
                                break;
                        }

                    }
                }
                list.add(emp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return list;
    }


}
