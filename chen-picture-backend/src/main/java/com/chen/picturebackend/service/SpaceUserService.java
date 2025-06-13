package com.chen.picturebackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.picturebackend.model.entity.SpaceUser;
import com.chen.picturebackend.model.entity.dto.spaceuser.SpaceUserAddRequest;
import com.chen.picturebackend.model.entity.dto.spaceuser.SpaceUserQueryRequest;
import com.chen.picturebackend.model.entity.vo.SpaceUserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author xlj
 * @date 2025-06-13
 * @description 针对表【space_user(空间用户关联)】的数据库操作Service
 */

public interface SpaceUserService extends IService<SpaceUser> {


    long addSpaceUser(SpaceUserAddRequest spaceUserAddRequest);

    void validSpaceUser(SpaceUser spaceUser, boolean add);

    QueryWrapper<SpaceUser> getQueryWrapper(SpaceUserQueryRequest spaceUserQueryRequest);

    SpaceUserVO getSpaceUserVO(SpaceUser spaceUser, HttpServletRequest request);

    List<SpaceUserVO> getSpaceUserVOList(List<SpaceUser> spaceUserList);
}