package com.randommeme.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.randommeme.entity.ContentPo;
import com.randommeme.entity.RecommendCountPo;
import com.randommeme.service.IContentService;
import com.randommeme.service.IRecommendService;
import com.randommeme.dao.IRecommendDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.randommeme.entity.RecommendPo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 推荐表  业务层访问接口实现类
 *
 * @author lsivt
 * @date 2021-06-23 10:41:53
 */
@Slf4j
@Service
public class RecommendServiceImpl extends ServiceImpl<IRecommendDao, RecommendPo> implements IRecommendService {

    @Autowired
    @Lazy
    private IContentService contentService;

    @Override
    public void recommend(Long userId, Long contentId, Integer recommend) {
        if (userId == null) {
            return;
        }
        ContentPo contentPo = contentService.getById(contentId);
        if (contentPo == null) {
            return;
        }
        RecommendPo exist = getOne(Wrappers.lambdaQuery(RecommendPo.class)
                .eq(RecommendPo::getContentId, contentId)
                .eq(RecommendPo::getUserId, userId), false);
        if (exist == null) {
            save(RecommendPo.builder()
                    .id(IdWorker.getId())
                    .contentId(contentId)
                    .userId(userId)
                    .recommend(recommend)
                    .build());
        } else {
            updateById(RecommendPo.builder()
                    .id(exist.getId())
                    .recommend(recommend)
                    .build());
        }
    }

    @Override
    public RecommendCountPo recommendCount(Long contentId) {
        RecommendCountPo po = getBaseMapper().recommendCount(contentId);
        if (po == null) {
            return RecommendCountPo.builder()
                    .recommend(0)
                    .notRecommend(0)
                    .build();
        }
        return po;
    }
}
