package com.web.mapper;

import com.web.entity.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    //查询所有部门
    //方法一：
    /*@Results({
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "update_time",property = "updateTime")
    })*/

    //方法二：起别名
    //@Select("select id, name, create_time createTime, update_time updateTime from dept order by update_time desc")

    //方法三：配置文件开启驼峰命名映射开关
    @Select("select id, name, create_time, update_time from dept order by update_time desc")
    List<Dept> findAll();

    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    @Insert("insert into dept(name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")
    void addDept(Dept dept);

    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept getById(Integer id);

    @Update("update dept set name = #{name} , update_time = #{updateTime} where id = #{id}")
    void updateDept(Dept dept);
}
