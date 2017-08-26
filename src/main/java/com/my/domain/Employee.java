package com.my.domain;

import org.apache.ibatis.type.Alias;

/**
 * Created by Administrator on 2017/8/21.
 */
@Alias("employee")
public class Employee {
    private int id;
    private String name;
    private String gender;
    private String email;
    private int dept_id;
    private Dept dept;

   public Employee(){
        super();
    }

    public Employee(String name, String gender, String email, int dept_id) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.dept_id = dept_id;
    }

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public String toString(){
        return "id= "+id+"name= "+name+"gender= "+gender+"email= "+email;//+"dept= "+dept.getId()+" "+dept.getName()
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }
}
