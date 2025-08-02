package com.web.service;


import com.web.entity.Dept;

import java.util.List;

public interface DeptService {
    void addDept(Dept dept);

    List<Dept> findAll();

    void deleteById(Integer id);

    Dept getById(Integer id);


    void updateDept(Dept dept);
}
