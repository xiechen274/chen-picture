package com.chen.picturebackend.service;

import cn.hutool.http.server.HttpServerRequest;
import com.chen.picturebackend.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.picturebackend.model.entity.vo.LoginUserVO;
import com.chen.picturebackend.model.entity.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);

    LoginUserVO getLoginUserVO(User user);

    User getLoginUser(HttpServletRequest request);

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    boolean userLogout(HttpServletRequest request);

    UserVO getUserVO(User user);

    List<UserVO> getUserVOList(List<User> userList);
    /**
     * 是否为管理员
     *
     * @param user
     * @return
     */
    boolean isAdmin(User user);

}
