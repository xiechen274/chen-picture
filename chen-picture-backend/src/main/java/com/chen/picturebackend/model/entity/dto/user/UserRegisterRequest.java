package com.chen.picturebackend.model.entity.dto.user;

import lombok.Data;

/**
 * @author xlj 2024-12-11
 */
@Data
public class UserRegisterRequest {
    // 用户账号
    private String userAccount;
    //用户密码
    private String userPassword;
    //确认密码
    private String checkPassword;
}
