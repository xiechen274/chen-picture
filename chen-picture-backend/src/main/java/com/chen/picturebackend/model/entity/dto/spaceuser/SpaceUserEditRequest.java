package com.chen.picturebackend.model.entity.dto.spaceuser;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xlj
 * @date 2025-06-12
 * @description 空间用户编辑请求
 */

@Data
public class SpaceUserEditRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 空间角色：viewer/editor/admin
     */
    private String spaceRole;

    private static final long serialVersionUID = 1L;
}
