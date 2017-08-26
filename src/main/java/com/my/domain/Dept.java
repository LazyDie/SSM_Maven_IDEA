package com.my.domain;

import java.util.List;

/**
 * Created by Administrator on 2017/8/20.
 */
public class Dept {
    private int id;
    private String name;
   private List<Employee> employees;

   public String toString(){
       return "id="+id+" name="+name;
   }
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Dept(){}
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
}
