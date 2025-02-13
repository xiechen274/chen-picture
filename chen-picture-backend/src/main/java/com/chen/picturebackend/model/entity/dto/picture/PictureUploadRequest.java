package com.chen.picturebackend.model.entity.dto.picture;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xlj 2024-12-29
 */
@Data
public class PictureUploadRequest implements Serializable {

    /**
     * 图片 id（用于修改）
     */
    private Long id;

    //文件下载地址(通过文件url下载文件)
    private String fileUrl;

    /**
     * 图片名称
     */
    private String picName;

    /**
     * 空间 id
     */
    private Long spaceId;

    private static final long serialVersionUID = 1L;
}
