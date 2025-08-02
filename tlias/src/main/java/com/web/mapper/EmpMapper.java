package com.web.mapper;

import com.web.entity.Emp;
import com.web.entity.EmpQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    //查询总记录数
    /*@Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
    public Long count();*/

    //分页查询
    /*@Select("select e.*,d.name deptName from emp e left join dept d on e.id = d.id " +
            "order by e.update_time desc limit #{start},#{pageSize}")
    public List<Emp> list(Integer start, Integer pageSize);*/

    //使用pagehelper
    /*@Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id " +
            "order by e.update_time desc")*/

    //现已使用xml实现sql查询
    public List<Emp> list(EmpQueryParam empQueryParam);

    @Options(useGeneratedKeys = true,keyProperty = "id") //主键返回，将数据库生成的主键返回到对象
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void addEmp(Emp emp);
}
