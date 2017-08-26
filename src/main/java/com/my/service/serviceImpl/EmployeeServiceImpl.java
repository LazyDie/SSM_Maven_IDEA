package com.my.service.serviceImpl;

import com.my.dao.EmployeeDao;
import com.my.domain.Employee;
import com.my.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 */

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private  EmployeeDao employeeDao;
    public Employee findAll(int id) {
        return employeeDao.findAll(id);
    }

    //查询所有员工
    public List<Employee> getAll() {
        return employeeDao.selectAllEmployee();
    }
    //批量插入员工数据
     public void insertEmployee(Employee employee){
        employeeDao.insertEmployee(employee);
}

    //保存员工
    public void saveEmp(Employee employee){employeeDao.saveEmp(employee);}
}
