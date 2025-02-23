package com.chen.picturebackend.model.entity.dto.picture;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xlj
 * @date 2025-02-23
 * @description 通过图片搜索图片
 */

@Data
public class SearchPictureByPictureRequest implements Serializable {

    /**
     * 图片 id
     */
    private Long pictureId;

    private static final long serialVersionUID = 1L;
}
