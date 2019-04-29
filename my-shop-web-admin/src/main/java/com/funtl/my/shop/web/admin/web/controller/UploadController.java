package com.funtl.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 文件上传控制器
 */
@Controller
public class UploadController {

    private static final String UPLOAD_PATH="/static/upload/";
    /**
     * 文件上传
     *dropFile dropzone de
     * editor wangEditor
     *
     * @return
     */
    //上传文件肯定不跳转页面，所以肯定要返回json来展示数据，这里用map的形式展示
    @ResponseBody
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public Map<String,Object> upload(MultipartFile dropFile, MultipartFile editorFile,HttpServletRequest request){
        Map<String, Object> result = new HashMap<>();
        //前端上传的文件
        MultipartFile myFile=dropFile ==null?editorFile:dropFile;
        //文件名
        String fileName=myFile.getOriginalFilename();
        String fileSuffix=fileName.substring(fileName.lastIndexOf("."));
//        文件存放路径
        System.out.println(request.getSession().getServletContext().getRealPath("/"));
        String filePath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);
        //判断路径是否存在，不存在则创建文件夹
        File file=new File(filePath);
        if (!file.exists()){
            file.mkdir();
        }

        //将文件写入目标
        file=new File(filePath, UUID.randomUUID()+fileSuffix);
        try {
            myFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        dropzone上传
        if(dropFile !=null){
            result.put("fileName", UPLOAD_PATH+file.getName());
        }
//        wangeditor上传
        else {
            /**
             * Scheme 服务端提供的协议 http/https
             * ServerName 服务器名称 localhost/ip/domain
             * ServerPort 服务器端口
             */
            String serverPath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
            System.out.println(serverPath);
            result.put("errno",0);
            result.put("data", new String[]{serverPath+UPLOAD_PATH+file.getName()});
        }

        return result;
    }

}
