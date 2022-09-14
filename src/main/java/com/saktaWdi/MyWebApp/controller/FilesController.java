package com.saktaWdi.MyWebApp.controller;

import com.saktaWdi.MyWebApp.utils.CommonResult;
import com.saktaWdi.MyWebApp.utils.FilesUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/pub/upload")
@Slf4j
public class FilesController {

    @PostMapping("avatar")
    public CommonResult uploadImg(@RequestParam(value="files",required = false)MultipartFile [] files) throws Exception{
        if(files==null){
            return CommonResult.fail("","");
        }
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            String fileType = file.getContentType();
            if(StringUtils.isEmpty(fileType) || !fileType.matches("image.*")){
                log.error("上传图片类型错误:" + fileType);
                return  CommonResult.fail("","上传图片类型错误");
            }
            if(file.getSize() > (long)(5 * 1024 * 1024)){
                log.error("上传图片大小超过限制:" + file.getSize());
                return CommonResult.fail("","上传图片大小超过限制(5Mb)");
            }
            String fileName = file.getOriginalFilename();
            String temp[] = fileName.split("\\.");
            if (temp.length < 2 || !temp[temp.length - 1].matches("(jpg|jpeg|png|JPG|JPEG|PNG)")) {
                log.error("上传图片文件名错误:" + fileName);
                return CommonResult.fail("","上传图片文件名错误");
            }
            byte[] imgCompress = FilesUtils.compressPicForScale(file.getBytes(), 300, file.getOriginalFilename());

            ApplicationHome h = new ApplicationHome(getClass());
            File jarF = h.getSource();
            String resourcesRootPath = jarF.getParentFile().toString()+"/logistics";



            String code = UUID.randomUUID().toString().replaceAll("-", "");
            String newFileName = code + fileName.substring(fileName.lastIndexOf('.'));
            String destFilePath = "userFiles/newUsers/"+newFileName;
            String savePath=resourcesRootPath+destFilePath;
            if(FilesUtils.base64StrToImage(imgCompress,savePath)){
                return CommonResult.ok().setResult(destFilePath);
            }else{
                return CommonResult.fail("-6","base64转图片失败");
            }
        }
        return CommonResult.fail();
    }
}
