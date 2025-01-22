package com.chen.picturebackend.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xlj 2024-12-09
 */
@Data
public class DeleteRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}
