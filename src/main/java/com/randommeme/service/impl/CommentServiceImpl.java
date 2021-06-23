package com.randommeme.service.impl;

import com.randommeme.service.ICommentService;
import com.randommeme.dao.ICommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import com.randommeme.entity.CommentPo;
import com.randommeme.convert.CommentConvert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 评论表  业务层访问接口实现类
 *
 * @author lsivt
 * @date 2021-06-23 10:41:53
 */
@Slf4j
@Service
public class CommentServiceImpl extends ServiceImpl<ICommentDao, CommentPo> implements ICommentService {

    @Autowired
    private CommentConvert commentConvert;
}
