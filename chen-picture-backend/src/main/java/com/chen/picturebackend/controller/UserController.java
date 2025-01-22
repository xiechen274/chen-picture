package com.chen.picturebackend.controller;

import cn.hutool.core.io.BOMInputStream;
import cn.hutool.http.server.HttpServerRequest;
import com.chen.picturebackend.annotation.AuthCheck;
import com.chen.picturebackend.common.BaseResponse;
import com.chen.picturebackend.common.ResultUtils;
import com.chen.picturebackend.constant.UserConstant;
import com.chen.picturebackend.exception.ErrorCode;
import com.chen.picturebackend.exception.ThrowUtils;
import com.chen.picturebackend.model.entity.User;
import com.chen.picturebackend.model.entity.dto.picture.PictureReviewRequest;
import com.chen.picturebackend.model.entity.dto.user.UserLoginRequest;
import com.chen.picturebackend.model.entity.dto.user.UserRegisterRequest;
import com.chen.picturebackend.model.entity.vo.LoginUserVO;
import com.chen.picturebackend.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author xlj 2024-12-11
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 用户注册
     * @param userRegisterRequest
     * @return
     */
    //@AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @PostMapping("/register")
    public BaseResponse register(@RequestBody UserRegisterRequest userRegisterRequest){
        String userAccount = userRegisterRequest.getUserAccount() != null ? userRegisterRequest.getUserAccount() : "";
        String userPassword = userRegisterRequest.getUserPassword() != null ? userRegisterRequest.getUserPassword() : "";
        String checkPassword = userRegisterRequest.getCheckPassword() != null ? userRegisterRequest.getCheckPassword() : "";
        long id = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(id,"注册成功");
    }

    /**
     * 用户登录
     * @param userLoginRequest
     * @param request
     * @return
     */
    @PostMapping("/login")
    public BaseResponse<LoginUserVO> login(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request){
        String userAccount = userLoginRequest.getUserAccount() != null ? userLoginRequest.getUserAccount() : "";
        String userPassword = userLoginRequest.getUserPassword() != null ? userLoginRequest.getUserPassword() : "";
        LoginUserVO loginUserVO = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(loginUserVO,"登录成功");
    }

    /**
     * 获取当前登录的用户信息
     * @param request
     * @return
     */
    @GetMapping("/get/login")
    public BaseResponse<LoginUserVO> getLoginUser(HttpServletRequest request) {
        //先拿到当前登录的user
        User user = userService.getLoginUser(request);
        //对user进行脱敏
        return ResultUtils.success(userService.getLoginUserVO(user));
    }

    /**
     * 用户注销(退出登录)
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public BaseResponse<Boolean> logout(HttpServletRequest request) {
        return ResultUtils.success(userService.userLogout(request),"注销成功");
    }

}
