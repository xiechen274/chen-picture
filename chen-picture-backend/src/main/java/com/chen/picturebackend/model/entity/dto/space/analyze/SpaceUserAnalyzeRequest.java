package com.chen.picturebackend.model.entity.dto.space.analyze;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xlj
 * @date 2025-05-12
 * @description 空间用户上传行为分析
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class SpaceUserAnalyzeRequest extends SpaceAnalyzeRequest {

    /**
     * 用户 ID
     */
    private Long userId;

    /**
     * 时间维度：day / week / month
     */
    private String timeDimension;
}
