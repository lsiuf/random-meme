package com.randommeme.service.impl;

import com.randommeme.service.IContentService;
import com.randommeme.dao.IContentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import com.randommeme.entity.ContentPo;
import com.randommeme.convert.ContentConvert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 内容表  业务层访问接口实现类
 *
 * @author lsivt
 * @date 2021-06-23 10:41:53
 */
@Slf4j
@Service
public class ContentServiceImpl extends ServiceImpl<IContentDao, ContentPo> implements IContentService {

    @Autowired
    private ContentConvert contentConvert;
}
