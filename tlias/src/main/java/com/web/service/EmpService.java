package com.web.service;

import com.web.entity.Emp;
import com.web.entity.EmpExpr;
import com.web.entity.EmpQueryParam;
import com.web.entity.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;


public interface EmpService {
//    PageResult<Emp> pageSelect(Integer page, Integer pageSize,String name, Integer gender, LocalDate begin, LocalDate end);
PageResult<Emp> pageSelect(EmpQueryParam empQueryParam);

    void addEmp(Emp emp);


}
