package com.chen.picturebackend.model.entity.dto.picture;

import lombok.Data;

/**
 * @author xlj 2025-01-20
 */
@Data
public class PictureUploadByBatchRequest {

    /**
     * 搜索词
     */
    private String searchText;

    /**
     * 抓取数量
     */
    private Integer count = 15;
}
