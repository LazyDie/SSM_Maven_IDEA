package com.my.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.domain.Employee;
import com.my.domain.Msg;
import com.my.service.EmployeeService;
import com.my.service.serviceImpl.EmployeeServiceImpl;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Administrator on 2017/8/23.
 */
@Controller
public class EmployeeController {

    @Autowired
    @Qualifier("employeeService")
    private EmployeeService employeeService;

    //员工保存
    @RequestMapping(value = "/emp",method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmps(Employee employee){
        employeeService.saveEmp(employee);
        return Msg.success();
    }

   /*====================================== 通过json方式==================================================*/
 /*   ResponseBody要返回json数据需要导入jackson包   查询所有员工信息包装在Msg中*/
    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmpsWithJson(@RequestParam(value="pn",defaultValue = "1") Integer pn){

        //分页查询 pageHelper分页插件
        //在查询之前只需要调用,插入页码以及分页每页的大小
        PageHelper.startPage(pn,5);
        //startPage后面紧跟的一个查询就是分页查询
        List<Employee> list = employeeService.getAll();
        //通过PageInfo 包装最后查询出来的数据，只需要 将PageInfo交给页面即可
        //封装了详细的页面信息，包括查询出来的数据
        PageInfo page = new PageInfo(list,5);//5表示要连续显示的页数
        return Msg.success().add("pageInfo",page);
    }


    /*=============================================不用ajax===========================================*/
    @RequestMapping("/login")
    public String show(@RequestParam("bianhao") String bianhao, HttpSession session){
        System.out.println(bianhao);
        System.out.println(Integer.parseInt(bianhao));
        Employee employee = employeeService.findAll(Integer.parseInt(bianhao));
        System.out.println(employee.getName());
        session.setAttribute("employee",employee.getName());
        return "success";
    }
    /**
     * 查询员工数据
     * 分页查询
     * @return
     */
   // @RequestMapping("/emps")
    public String getEmps(@RequestParam(value="pn",defaultValue = "1") Integer pn,
                          Model model){
        //分页查询 pageHelper分页插件
        //在查询之前只需要调用,插入页码以及分页每页的大小
        PageHelper.startPage(pn,5);
        //startPage后面紧跟的一个查询就是分页查询
        List<Employee> list = employeeService.getAll();
        //通过PageInfo 包装最后查询出来的数据，只需要 将PageInfo交给页面即可
        //封装了详细的页面信息，包括查询出来的数据
        PageInfo page = new PageInfo(list,5);//5表示要连续显示的页数
        model.addAttribute("pageInfo",page);

        return "list";
    }

}
