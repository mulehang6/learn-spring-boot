package com.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.web.entity.*;
import com.web.mapper.EmpExprMapper;
import com.web.mapper.EmpMapper;
import com.web.service.EmpLogService;
import com.web.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;

    /*@Override
    public PageResult<Emp> pageSelect(Integer page, Integer pageSize) {
        //调用mapper接口，查询总记录数
        Long total = empMapper.count();

        //调用mapper接口，查询结果列表
        List<Emp> rows = empMapper.list((page - 1) * pageSize,pageSize);

        //封装到PageResult
        return new PageResult<>(total,rows);
    }*/

    /*@Override
    public PageResult<Emp> pageSelect(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
        //调用mapper接口，查询总记录数
        PageHelper.startPage(page,pageSize);

        //调用mapper接口，查询结果列表
        List<Emp> rows = empMapper.list(name,gender,begin,end);

        //封装到PageResult
        Page<Emp> emps = (Page<Emp>) rows;
        return new PageResult<Emp>(emps.getTotal(),emps.getResult());
    }*/

    @Override
    public PageResult<Emp> pageSelect(EmpQueryParam empQueryParam) {
        //调用mapper接口，查询总记录数
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());

        //调用mapper接口，查询结果列表
        List<Emp> rows = empMapper.list(empQueryParam);

        //封装到PageResult
        Page<Emp> emps = (Page<Emp>) rows;
        return new PageResult<>(emps.getTotal(),emps.getResult());
    }

    @Transactional(rollbackFor = {Exception.class}) // 默认RunTimeException回滚
    @Override
    public void addEmp(Emp emp) {
        try {
            //封装到emp
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.addEmp(emp);


            //封装到empExpr
            List<EmpExpr> empExprs = emp.getExprList();
            if(!empExprs.isEmpty()) {
                //遍历集合，为deptId赋值
                empExprs.forEach(expr -> {
                    expr.setEmpId(emp.getId());
                });
                empExprMapper.addExprs(empExprs);
            }
        } finally {
            // 记录日志
            EmpLog empLog = new EmpLog(null,LocalDateTime.now(),"信息: " + emp);
            empLogService.insertLog(empLog);
        }
    }
}
