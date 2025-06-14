package com.chen.picturebackend.model.entity.dto.space;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import com.chen.picturebackend.common.PageRequest;


/**
 * @author xlj
 * @date 2025-02-12
 * @description 查询请求
 */

/**
 * 查询空间请求
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class SpaceQueryRequest extends PageRequest implements Serializable {

    /**
     * 空间类型：0-私有 1-团队
     */
    private Integer spaceType;

    /**
     * id
     */
    private Long id;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 空间名称
     */
    private String spaceName;

    /**
     * 空间级别：0-普通版 1-专业版 2-旗舰版
     */
    private Integer spaceLevel;

    private static final long serialVersionUID = 1L;
}

