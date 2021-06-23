package com.randommeme.dao;

import com.randommeme.entity.RecommendPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 推荐表  数据层访问接口
 *
 * @author lsivt
 * @date 2021-06-23 10:41:53
 */
@Mapper
public interface IRecommendDao extends BaseMapper<RecommendPo> {
}