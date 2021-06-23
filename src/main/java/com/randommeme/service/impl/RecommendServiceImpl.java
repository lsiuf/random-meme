package com.randommeme.service.impl;

import com.randommeme.service.IRecommendService;
import com.randommeme.dao.IRecommendDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.randommeme.entity.RecommendPo;
import com.randommeme.convert.RecommendConvert;
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
    private RecommendConvert recommendConvert;
}
