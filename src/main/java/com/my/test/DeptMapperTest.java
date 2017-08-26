package com.my.test;

import com.my.domain.Dept;
import com.my.service.DeptService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Administrator on 2017/8/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class DeptMapperTest {

    @Autowired
    @Qualifier("deptService")
    DeptService deptService;
    @Test
    public void show(){
        List<Dept> list = deptService.getDepts();
        for (Dept d:list
                ) {
            System.out.println(d.getName());
        }
    }
}
