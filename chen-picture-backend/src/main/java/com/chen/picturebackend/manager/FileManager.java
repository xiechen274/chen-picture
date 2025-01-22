package com.chen.picturebackend.manager;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import com.chen.picturebackend.config.CosClientConfig;
import com.chen.picturebackend.exception.BusinessException;
import com.chen.picturebackend.exception.ErrorCode;
import com.chen.picturebackend.exception.ThrowUtils;
import com.chen.picturebackend.model.entity.dto.file.UploadPictureResult;
import com.qcloud.cos.demo.PutObjectDemo;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.model.ciModel.persistence.ImageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author xlj 2024-12-29
 */
@Service
@Slf4j
@Deprecated
public class FileManager {
    @Resource
    private CosClientConfig cosClientConfig;

    @Resource
    private CosManager cosManager;

    /**
     * 上传图片
     *
     * @param multipartFile    文件
     * @param uploadPathPrefix 上传路径前缀
     * @return
     */
        public UploadPictureResult uploadPicture(MultipartFile multipartFile, String uploadPathPrefix) {
            //文件校验
            validPicture(multipartFile);

            //封装图片上传的路径和图片名字
            String uuid = RandomUtil.randomString(16);
            String originFilename = multipartFile.getOriginalFilename();
            String uploadFilename = String.format("%s_%s.%s",
                    DateUtil.formatDate(new Date()),
                    uuid, FileUtil.getSuffix(originFilename));
            String uploadPath = String.format("%s/%s", uploadPathPrefix, uploadFilename);

            //上传图片
            File file = null;
            try {
                //创建临时文件
                file = File.createTempFile(uploadPath, null);
                multipartFile.transferTo(file);
                //上传到cos
                PutObjectResult putObjectResult = cosManager.putPictureObject(uploadPath, file);
                //利用cos的imageinfo对象获取一些图片参数并且设置图片参数
                ImageInfo imageInfo = putObjectResult.getCiUploadResult().getOriginalInfo().getImageInfo();
                // 封装返回结果  ,下面这些代码参考数据万象的官方文档代码
                UploadPictureResult uploadPictureResult = new UploadPictureResult();
                int picWidth = imageInfo.getWidth();
                int picHeight = imageInfo.getHeight();
                double picScale = NumberUtil.round(picWidth * 1.0 / picHeight, 2).doubleValue();
                uploadPictureResult.setPicName(FileUtil.mainName(originFilename));
                uploadPictureResult.setPicWidth(picWidth);
                uploadPictureResult.setPicHeight(picHeight);
                uploadPictureResult.setPicScale(picScale);
                uploadPictureResult.setPicFormat(imageInfo.getFormat());
                uploadPictureResult.setPicSize(FileUtil.size(file));
                uploadPictureResult.setUrl(cosClientConfig.getHost() + "/" + uploadPath);
                return uploadPictureResult;
            } catch (IOException e) {
                log.error("图片上传到对象存储失败", e);
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "上传失败");
            }finally {
                //销毁文件
                this.deleteTempFile(file);
            }


        }
        //删除临时文件
        private void deleteTempFile(File file) {
            if(file != null){
                return;
            }
            boolean deleteResult = file.delete();
            if(!deleteResult){
                log.error("临时文件删除失败",file.getAbsolutePath());
            }
        }

        //图片格式校验
        private void validPicture(MultipartFile multipartFile) {
            //图片上传地址
            if(multipartFile == null){
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"文件为空");
            }
            //检查图片大小
            if(multipartFile.getSize() > 1024 * 1024 * 2){
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "文件大小不能超过2M");
            }
            //检查文件后缀
            String suffix = FileUtil.getSuffix(multipartFile.getOriginalFilename());
            List<String> suffixList = Arrays.asList("png", "jpg", "jpeg", "bmp", "gif");
            if(!suffixList.contains(suffix)){
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "文件格式不正确");
            }
        }

}
