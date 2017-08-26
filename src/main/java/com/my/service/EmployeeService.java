package com.my.service;

import com.my.domain.Employee;

import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 */
public interface EmployeeService {
    public Employee findAll(int id);

    //分页查询所有员工
    public List<Employee> getAll();
    //批量插入员工数据
    void insertEmployee(Employee employee);

    void saveEmp(Employee employee);
}
