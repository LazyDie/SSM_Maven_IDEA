package com.my.dao;

import com.my.domain.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 */
@Repository
public interface EmployeeDao {
    Employee findAll(int id);

    //查询所有员工 并用分页显示
    List<Employee> selectAllEmployee();

    //插入员工，通过批量插入
    void insertEmployee(Employee employee);

}
