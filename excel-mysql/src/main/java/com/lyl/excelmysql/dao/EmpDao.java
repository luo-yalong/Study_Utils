package com.lyl.excelmysql.dao;

import com.lyl.excelmysql.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EmpDao {

    public List<Emp> selectAll();

    /**
     * 获取表中的列
     * @param table_schema  数据库名称
     * @param table_name  数据表名称
     * @return 返回对应数据表的字段
     */
    public List<String> getAllColumn(@Param("table_schema") String table_schema, @Param("table_name") String table_name);
}
