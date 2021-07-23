package com.randommeme.service;

import com.randommeme.entity.RecommendCountPo;
import com.randommeme.entity.RecommendPo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 推荐表  业务层访问接口
 *
 * @author lsivt
 * @date 2021-06-23 10:41:53
 */
public interface IRecommendService extends IService<RecommendPo> {

    /**
     * 推荐
     *
     * @param userId
     * @param contentId
     * @param recommend
     */
    void recommend(Long userId, Long contentId, Integer recommend);

    /**
     * 统计推荐数
     *
     * @param contentId
     * @return
     */
    RecommendCountPo recommendCount(Long contentId);
}