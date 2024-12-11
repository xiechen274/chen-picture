package com.chen.picturebackend.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.picturebackend.exception.BusinessException;
import com.chen.picturebackend.exception.ErrorCode;
import com.chen.picturebackend.model.entity.User;
import com.chen.picturebackend.service.UserService;
import com.chen.picturebackend.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 谢隆杰
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2024-12-11 11:57:07
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
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
}




