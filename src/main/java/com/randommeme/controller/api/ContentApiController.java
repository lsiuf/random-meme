package com.randommeme.controller.api;

import com.randommeme.common.util.TokenUtils;
import com.randommeme.dto.ContentOutDto;
import com.randommeme.service.IContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * 内容表  控制器
 *
 * @author lsivt
 * @date 2021-06-23 10:41:53
 */
@Slf4j
@RestController
@RequestMapping("/api/content")
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class ContentApiController {

    @Autowired
    private IContentService contentService;

    @GetMapping("/randomGet")
    public ContentOutDto randomGet(HttpServletRequest httpServletRequest) {
        return contentService.getContent(TokenUtils.getUserIP(httpServletRequest));
    }
}
