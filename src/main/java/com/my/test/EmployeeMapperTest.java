package com.my.test;

import com.my.dao.EmployeeDao;
import com.my.domain.Employee;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * Created by Administrator on 2017/8/22.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class EmployeeMapperTest {


        @Autowired
        private  EmployeeDao employeeDao;

        //批量的SqlSession
        @Autowired
        SqlSession sqlSession;
        @Test
        public void test() {
            /*Employee employee = employeeDao.findAll(1);
            System.out.println(employee.getName());*/
            //employeeDao.insertEmployee(new Employee("金天锋","M","111",1));
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            for(int i =0 ;i<100;i++){
               String name= UUID.randomUUID().toString().substring(0,5)+i;
                employeeDao.insertEmployee(new Employee(name,"M",name+"@qq.com",1));
            }

        }

}
