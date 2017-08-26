package com.my.controller;

import com.my.domain.Dept;
import com.my.domain.Msg;
import com.my.service.DeptService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/8/26.
 */
//发送所有部门信息
@Controller
public class DeptController {

    @Autowired
    @Qualifier("deptService")
    private DeptService deptService;
    @RequestMapping("/depts")
    @ResponseBody
    public Msg getDepts(){

        List<Dept> list = deptService.getDepts();
        return Msg.success().add("depts",list);
    }
}
