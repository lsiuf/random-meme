package com.randommeme.controller.back;

import com.randommeme.service.IContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

/**
 * 内容表  控制器
 *
 * @author lsivt
 * @date 2021-06-23 10:41:53
 */
@Slf4j
@RestController
@RequestMapping("/admin/content")
public class ContentBackController {

    @Autowired
    private IContentService contentService;
}
