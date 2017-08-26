package com.my.dao;

import com.my.domain.Dept;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/8/26.
 */
@Repository
public interface DeptDao {
    //查出所有部门信息
    List<Dept> getDepts();
}
