package com.web.controller;

import com.web.entity.*;
import com.web.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    /*@GetMapping
    public Result pageSelect(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                             String name, Integer gender,
                             @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                             @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询... {},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        PageResult<Emp> pageResult = empService.pageSelect(page,pageSize,name,gender,begin,end);
        return Result.success(pageResult);
    }*/

    @GetMapping
    public Result pageSelect(EmpQueryParam empQueryParam) {
        log.info("分页查询... {}",empQueryParam);
        PageResult<Emp> pageResult = empService.pageSelect(empQueryParam);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result addEmp(@RequestBody Emp emp) {
        log.info("添加员工... {}",emp);
        empService.addEmp(emp);

        return Result.success();
    }
}
