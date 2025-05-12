package com.chen.picturebackend.model.entity.dto.space.analyze;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xlj
 * @date 2025-05-12
 * @description 空间图片大小分析
 * 按图片大小（如 <100 KB、100 KB-1 MB、>1 MB）分段统计图片数量，帮助用户识别大体积图片，合理分配存储资源。由于按图片大小分类的数量不多，可以使用 饼图 展示，能够体现每类大小图片的数量占比。
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class SpaceSizeAnalyzeRequest extends SpaceAnalyzeRequest {

}
