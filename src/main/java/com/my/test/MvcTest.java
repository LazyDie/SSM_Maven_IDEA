package com.my.test;

import com.github.pagehelper.PageInfo;
import com.my.domain.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * 使用Spring测试模块提供的测试请求功能，测试CRUD的正确性
 * Created by Administrator on 2017/8/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath*:spring-mybatis.xml","classpath:springmvc-config.xml"})
public class MvcTest {
    //传入SpringMvc的ioc
    @Autowired
    WebApplicationContext context;
    //虚拟的Mvc请求，获取到处理结果
    MockMvc mockMvc;
    @Before
    public void initMokcMvc(){
      mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testPage() throws Exception {
        //模拟发送请求 拿到返回值
         MvcResult result= mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn","10"))
                .andReturn();
         //请求成功后，请求域中会有pageInfo，可以取出进行验证
        MockHttpServletRequest request =  result.getRequest();
        PageInfo pageInfo=(PageInfo)request.getAttribute("pageInfo");
        System.out.println(pageInfo==null);
        System.out.println("当前页码："+pageInfo.getPageNum());
        System.out.println("总页码:"+pageInfo.getPages());
        System.out.println("总记录数 "+pageInfo.getTotal());
        System.out.println("在页面需要连续显示的页码:");
        int[] nums  = pageInfo.getNavigatepageNums();
        for (int i : nums
             ) {
            System.out.print(" "+i);
        }
        System.out.println();
        //获取员工数据
        List<Employee> list = pageInfo.getList();
        for (Employee e:list
             ) {
            System.out.println(e.getName());
        }
    }

}
