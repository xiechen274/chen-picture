package com.chen.picturebackend.model.entity.dto.space;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xlj 2025-02-12
 */
@Data
public class SpaceAddRequest implements Serializable {

    /**
     * 空间名称
     */
    private String spaceName;

    /**
     * 空间类型：0-私有 1-团队
     */
    private Integer spaceType;


    /**
     * 空间级别：0-普通版 1-专业版 2-旗舰版
     */
    private Integer spaceLevel;

    private static final long serialVersionUID = 1L;

}
