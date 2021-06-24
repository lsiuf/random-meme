package com.randommeme.controller.api;

import com.randommeme.service.IClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

/**
 * 分类表  控制器
 *
 * @author lsivt
 * @date 2021-06-24 15:44:26
 */
@Slf4j
@RestController
@RequestMapping("/api/classify")
public class ClassifyApiController {

    @Autowired
    private IClassifyService classifyService;

}
