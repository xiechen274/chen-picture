package com.chen.picturebackend.model.entity.dto.picture;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xlj
 * @date 2025-02-23
 * @description 以颜色搜图
 */

@Data
public class SearchPictureByColorRequest implements Serializable {

    /**
     * 图片主色调
     */
    private String picColor;

    /**
     * 空间 id
     */
    private Long spaceId;

    private static final long serialVersionUID = 1L;
}