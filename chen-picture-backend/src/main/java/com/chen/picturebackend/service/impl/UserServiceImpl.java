package com.chen.picturebackend.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.picturebackend.exception.BusinessException;
import com.chen.picturebackend.exception.ErrorCode;
import com.chen.picturebackend.manager.auth.StpKit;
import com.chen.picturebackend.model.entity.User;
import com.chen.picturebackend.model.entity.enmus.UserRoleEnum;
import com.chen.picturebackend.model.entity.vo.LoginUserVO;
import com.chen.picturebackend.model.entity.vo.UserVO;
import com.chen.picturebackend.service.UserService;
import com.chen.picturebackend.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.chen.picturebackend.constant.UserConstant.USER_LOGIN_STATE;

/**
* @author 谢隆杰
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2024-12-11 11:57:07
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        if(StrUtil.isEmpty(userAccount) || StrUtil.isEmpty(userPassword) || StrUtil.isEmpty(checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号或者密码为空");
        }
        //1.对参数进行适当的校验
        if(!userPassword.equals(checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"两次密码不一致");
        }
        if(userAccount.length()<4){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户账号过短,请输入大于四位的数");
        }
        if (!userPassword.matches(".*[a-z].*") || !userPassword.matches(".*[A-Z].*") || !userPassword.matches(".*\\d.*")) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码必须包含大小写字母和数字");
        }

        //2.检验数据库中是否已经有了
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        long count = this.count(queryWrapper);
        if (count > 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户账号已存在");
        }
        //3.加密密码
        String encryptPassword = encryptPassword(userPassword);
        //4.插入数据库
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        user.setUserName("无名");
        boolean save = this.save(user);
        return 0;
    }
    @Override
    public String encryptPassword(String password){
        String salt = "xiechen";
        return DigestUtil.md5Hex(password + salt);
    }

    @Override
    public LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        if(StrUtil.isEmpty(userAccount) || StrUtil.isEmpty(userPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号或者密码为空");
        }
        //检验用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        queryWrapper.eq("userPassword",encryptPassword(userPassword));
        User user = this.baseMapper.selectOne(queryWrapper);
        if(user == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户不存在或者密码错误");
        }

        //保存用户信息到Sesison中
        request.getSession().setAttribute(USER_LOGIN_STATE,user);
        //额外引入sa-token记录用户登录身份
        // 4. 记录用户登录态到 Sa-token，便于空间鉴权时使用，注意保证该用户信息与 SpringSession 中的信息过期时间一致
        StpKit.SPACE.login(user.getId());
        StpKit.SPACE.getSession().set(USER_LOGIN_STATE, user);
        //返回脱敏后的用户信息
        return this.getLoginUserVO(user);
    }
    @Override
    public LoginUserVO getLoginUserVO(User user) {
        if (user == null) {
            return null;
        }
        LoginUserVO loginUserVO = new LoginUserVO();
        BeanUtils.copyProperties(user, loginUserVO);
        return loginUserVO;
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        //先判断是否登录
        User user = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        if (user == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR,"用户未登录");
        }
        //从数据库中查询，保证一致性
        long userId = user.getId();
        user = this.getById(userId);
        if (user == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR,"用户不存在");
        }
        return user;
    }

    //注销用户（退出登录）
    @Override
    public boolean userLogout(HttpServletRequest request) {
        if(!isLogin(request)){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR,"用户未登录");
        }
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return true;
    }

    //判断用户是否登录
    public boolean isLogin(HttpServletRequest request) {
        return request.getSession().getAttribute(USER_LOGIN_STATE) != null;
    }
    @Override
    public UserVO getUserVO(User user) {
        if (user == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    public List<UserVO> getUserVOList(List<User> userList) {
        if (CollUtil.isEmpty(userList)) {
            return new ArrayList<>();
        }
        return userList.stream().map(this::getUserVO).collect(Collectors.toList());
    }

    /**
     * 判断用户是否是管理员
     * @param user
     * @return
     */
    @Override
    public boolean isAdmin(User user) {
        return user != null && UserRoleEnum.ADMIN.getValue().equals(user.getUserRole());
    }


}




