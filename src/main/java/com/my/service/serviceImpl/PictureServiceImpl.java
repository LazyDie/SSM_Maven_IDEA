package com.my.service.serviceImpl;

import com.my.dao.PictureDao;
import com.my.domain.Picture;
import com.my.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/9/10.
 */
@Service("pictureService")
public class PictureServiceImpl implements PictureService{

    @Autowired
    private PictureDao pictureDao;
    public void imageAdd(String image) {
        pictureDao.imageAdd(image);
    }
}
