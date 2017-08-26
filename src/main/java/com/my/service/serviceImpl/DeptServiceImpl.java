package com.my.service.serviceImpl;

import com.my.dao.DeptDao;
import com.my.domain.Dept;
import com.my.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/8/26.
 */
@Service("deptService")
public class DeptServiceImpl implements DeptService{
    @Autowired
    DeptDao deptDao;
    public List<Dept> getDepts() {
        return deptDao.getDepts();
    }
}
