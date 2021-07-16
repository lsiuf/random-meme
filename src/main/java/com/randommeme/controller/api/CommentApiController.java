package com.randommeme.controller.api;

import com.randommeme.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

/**
 * 评论表  控制器
 *
 * @author lsivt
 * @date 2021-06-23 10:41:53
 */
@Slf4j
@RestController
@RequestMapping("/api/comment")
public class CommentApiController {

    @Autowired
    private ICommentService commentService;
}
