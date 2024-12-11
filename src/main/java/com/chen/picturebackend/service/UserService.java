package com.chen.picturebackend.service;

import com.chen.picturebackend.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 谢隆杰
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2024-12-11 11:57:07
*/
public interface UserService extends IService<User> {
    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    String encryptPassword(String password);
}
