package com.saktaWdi.MyWebApp.utils;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;

/**
 * 文件操作工具
 */
@Slf4j
public class FilesUtils {

    public String saveFile(MultipartFile file, HttpServletRequest request, int index, int userNum) {
        String fileName = file.getOriginalFilename();
        String code = UUID.randomUUID().toString().replaceAll("-", "");
        String newFileName = code + fileName.substring(fileName.lastIndexOf('.'));
        String resourcesRootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String destFilePath;

        destFilePath = "public/userFiles/" + userNum + "/" + "Img" + newFileName;


        try {
            File destFile = new File(resourcesRootPath + destFilePath);
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }
            file.transferTo(destFile);
            return destFilePath;
        } catch (Exception e) {
            log.info("保存文件失败");
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 压缩图片，通过谷歌的一个图片压缩工具compressPicForScale
     *
     * @param imageBytes
     * @param desFileSize
     * @param imageId
     * @return
     */
    public static byte[] compressPicForScale(byte[] imageBytes, long desFileSize, String imageId) {
        if (imageBytes == null || imageBytes.length <= 0 || imageBytes.length < desFileSize * 1024) {
            return imageBytes;
        }
        long srcSize = imageBytes.length;
        double accuracy = getAccuracy(srcSize / 1024);
        try {
            while (imageBytes.length > desFileSize * 1024) {
                ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream(imageBytes.length);
                Thumbnails.of(inputStream).scale(accuracy).outputQuality(accuracy).toOutputStream(outputStream);
                imageBytes = outputStream.toByteArray();
            }
            log.info("【图片压缩】imageId={} | 图片原大小={}kb | 压缩后大小={}kb", imageId, srcSize / 1024,
                    imageBytes.length / 1024);
        } catch (Exception e) {
            log.error("【图片压缩】msg=图片压缩失败!", e);
        }
        return imageBytes;
    }

    /**
     * 自动调节精度(经验数值)
     *
     * @param size 源图片大小
     * @return 图片压缩质量比
     */
    private static double getAccuracy(long size) {
        double accuracy;
        if (size < 900) {
            accuracy = 0.85;
        } else if (size < 2047) {
            accuracy = 0.6;
        } else if (size < 3275) {
            accuracy = 0.44;
        } else {
            accuracy = 0.4;
        }
        return accuracy;
    }

    /**
     * base64编码字符串转换为图片
     * @param imgStr base64编码字符串
     * @param path   图片路径
     * @return
     */
    public static boolean base64StrToImage(byte [] imgStr, String path) {
        if (imgStr == null)
            return false;
//        Base64.Decoder decoder = Base64.getDecoder();
        try {
            // 解密
//            byte[] b = decoder.decode(imgStr);
            byte []b=imgStr;
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            // 文件夹不存在则自动创建
            File tempFile = new File(path);
            if (!tempFile.getParentFile().exists()) {
                tempFile.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(tempFile);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
