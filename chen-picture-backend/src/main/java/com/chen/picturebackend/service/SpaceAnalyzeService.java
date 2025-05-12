package com.chen.picturebackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.picturebackend.model.entity.Space;
import com.chen.picturebackend.model.entity.User;
import com.chen.picturebackend.model.entity.dto.space.analyze.SpaceCategoryAnalyzeRequest;
import com.chen.picturebackend.model.entity.dto.space.analyze.SpaceRankAnalyzeRequest;
import com.chen.picturebackend.model.entity.dto.space.analyze.SpaceSizeAnalyzeRequest;
import com.chen.picturebackend.model.entity.dto.space.analyze.SpaceTagAnalyzeRequest;
import com.chen.picturebackend.model.entity.dto.space.analyze.SpaceUsageAnalyzeRequest;
import com.chen.picturebackend.model.entity.dto.space.analyze.SpaceUserAnalyzeRequest;
import com.chen.picturebackend.model.entity.vo.space.analyze.SpaceCategoryAnalyzeResponse;
import com.chen.picturebackend.model.entity.vo.space.analyze.SpaceSizeAnalyzeResponse;
import com.chen.picturebackend.model.entity.vo.space.analyze.SpaceTagAnalyzeResponse;
import com.chen.picturebackend.model.entity.vo.space.analyze.SpaceUsageAnalyzeResponse;
import com.chen.picturebackend.model.entity.vo.space.analyze.SpaceUserAnalyzeResponse;

import java.util.List;

/**
 * @author xlj
 * @date 2025-05-12
 * @description 这里是自动生成的文件注释
 */

public interface  SpaceAnalyzeService extends IService<Space> {

    /**
     * 获取空间使用情况分析
     *
     * @param spaceUsageAnalyzeRequest
     * @param loginUser
     * @return
     */
    SpaceUsageAnalyzeResponse getSpaceUsageAnalyze(SpaceUsageAnalyzeRequest spaceUsageAnalyzeRequest, User loginUser);

    /**
     * 获取空间图片分类分析
     *
     * @param spaceCategoryAnalyzeRequest
     * @param loginUser
     * @return
     */
    List<SpaceCategoryAnalyzeResponse> getSpaceCategoryAnalyze(SpaceCategoryAnalyzeRequest spaceCategoryAnalyzeRequest, User loginUser);

    List<SpaceTagAnalyzeResponse> getSpaceTagAnalyze(SpaceTagAnalyzeRequest spaceTagAnalyzeRequest, User loginUser);

    List<SpaceSizeAnalyzeResponse> getSpaceSizeAnalyze(SpaceSizeAnalyzeRequest spaceSizeAnalyzeRequest, User loginUser);

    List<SpaceUserAnalyzeResponse> getSpaceUserAnalyze(SpaceUserAnalyzeRequest spaceUserAnalyzeRequest, User loginUser);

    List<Space> getSpaceRankAnalyze(SpaceRankAnalyzeRequest spaceRankAnalyzeRequest, User loginUser);
}
