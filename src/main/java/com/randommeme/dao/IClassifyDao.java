package com.randommeme.dao;

import com.randommeme.entity.ClassifyPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 分类表  数据层访问接口
 *
 * @author lsivt
 * @date 2021-06-24 15:44:26
 */
@Mapper
public interface IClassifyDao extends BaseMapper<ClassifyPo> {
}