package com.randommeme.dao;

import com.randommeme.entity.ContentExtremeValuePo;
import com.randommeme.entity.ContentPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 内容表  数据层访问接口
 *
 * @author lsivt
 * @date 2021-06-23 10:41:53
 */
@Mapper
public interface IContentDao extends BaseMapper<ContentPo> {

    /**
     * 查询最小主键和最大主键
     *
     * @return
     */
    ContentExtremeValuePo getMinAndMaxId(@Param("classifyCode") String classifyCode);
}