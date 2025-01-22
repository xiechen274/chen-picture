package com.chen.picturebackend.model.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xlj 2024-12-13
 */
@Data
public class UserVO implements Serializable{
/**
 * 用户 id
     */
    private Long id;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户简介
     */
    private String userProfile;

    /**
     * 用户角色：user/admin
     */
    private String userRole;



    private static final long serialVersionUID = 1L;
}
