package com.chen.picturebackend.model.entity.dto.space;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xlj 2025-02-12
 */
@Data
public class SpaceEditRequest implements Serializable {

    /**
     * 空间 id
     */
    private Long id;

    /**
     * 空间名称
     */
    private String spaceName;

    private static final long serialVersionUID = 1L;
}

