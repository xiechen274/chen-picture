package com.chen.picturebackend.controller;

import com.chen.picturebackend.common.BaseResponse;
import com.chen.picturebackend.common.ResultUtils;
import com.chen.picturebackend.model.entity.dto.user.UserRegisterRequest;
import com.chen.picturebackend.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xlj 2024-12-11
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @PostMapping("/regitster")
    public BaseResponse register(@RequestBody UserRegisterRequest userRegisterRequest){
        String userAccount = userRegisterRequest.getUserAccount() != null ? userRegisterRequest.getUserAccount() : "";
        String userPassword = userRegisterRequest.getUserPassword() != null ? userRegisterRequest.getUserPassword() : "";
        String checkPassword = userRegisterRequest.getCheckPassword() != null ? userRegisterRequest.getCheckPassword() : "";
        long id = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(id,"注册成功");
    }
}
