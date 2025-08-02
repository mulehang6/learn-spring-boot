package com.web.controller;

import com.web.entity.Dept;
import com.web.entity.Result;
import com.web.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    //@RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping
    public Result findAll() {
        log.info("查询全部部门信息");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    @DeleteMapping //RequestParam在参数名和数据库字段名相同时可省略
    public Result deleteDept(Integer id) {
        log.info("删除部门id: {}",id);
        deptService.deleteById(id);
        return Result.success();
    }

    @PostMapping
    public Result addDept(@RequestBody Dept dept) {
        log.info("新增的部门: {}",dept);
        deptService.addDept(dept);
        return Result.success();
    }

    @GetMapping("{id}") // PathVariable在与形参名相同时也可省略value值
    public Result getById(@PathVariable Integer id) {
        log.info("查询的部门id: {}",id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    @PutMapping
    public Result updateDept(@RequestBody Dept dept) {
        log.info("更新的部门信息: {}",dept);
        deptService.updateDept(dept);
        return Result.success();
    }
}
