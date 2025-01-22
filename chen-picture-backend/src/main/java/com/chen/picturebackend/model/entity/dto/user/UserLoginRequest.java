package com.chen.picturebackend.model.entity.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xlj 2024-12-11
 */
@Data
public class UserLoginRequest implements Serializable {

    //序列化通过这个来确定序列化前后有没有正确
    private static final long serialVersionUID = 3191241716373120793L;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;
}
