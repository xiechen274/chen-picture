package com.chen.picturebackend.model.entity.dto.space.analyze;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xlj
 * @date 2025-05-12
 * @description 空间使用排行分析
 */

@Data
public class SpaceRankAnalyzeRequest implements Serializable {

    /**
     * 排名前 N 的空间
     */
    private Integer topN = 10;

    private static final long serialVersionUID = 1L;
}
