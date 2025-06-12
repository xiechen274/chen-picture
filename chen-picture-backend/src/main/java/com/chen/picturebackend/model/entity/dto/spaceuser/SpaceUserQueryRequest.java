package com.chen.picturebackend.model.entity.dto.spaceuser;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xlj
 * @date 2025-06-12
 * @description 这里是自动生成的文件注释
 */

@Data
public class SpaceUserQueryRequest implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 空间 ID
     */
    private Long spaceId;

    /**
     * 用户 ID
     */
    private Long userId;

    /**
     * 空间角色：viewer/editor/admin
     */
    private String spaceRole;

    private static final long serialVersionUID = 1L;
}

