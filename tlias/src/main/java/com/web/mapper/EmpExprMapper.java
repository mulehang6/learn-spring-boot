package com.web.mapper;

import com.web.entity.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {

    void addExprs(List<EmpExpr> empExprs);

    void delete(List<Integer> ids);
}
