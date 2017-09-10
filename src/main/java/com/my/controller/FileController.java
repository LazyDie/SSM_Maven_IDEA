package com.my.controller;

import com.my.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class FileController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String fileUpload(@RequestParam("file") MultipartFile file,
                             HttpSession session,
                             HttpServletRequest request){
        if(!file.isEmpty()){
            String path = request.getServletContext().getRealPath("/imags/");
            System.out.println(path);
            String str = (new SimpleDateFormat("yyyymmddhhmmss")).format(new Date());
            System.out.println(str);
            String filename = file.getOriginalFilename();
            File filepath = new File(path,filename);
            if(!filepath.getParentFile().exists()){
                filepath.getParentFile().mkdirs();
            }

            String[] s=filename.split("\\.");
            str=str+"."+s[1];
            try {
                File file1 = new File(path+File.separator+str);
                file.transferTo(file1);
                System.out.println(file1.getAbsolutePath());
                session.setAttribute("file",str);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "nice";
        }else
            return "error";

    }

}
