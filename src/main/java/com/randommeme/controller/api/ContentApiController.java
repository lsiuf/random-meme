package com.randommeme.controller.api;

import com.randommeme.dto.ContentDto;
import com.randommeme.service.IContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

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
    public ContentDto randomGet() {
        //TODO userId
        return contentService.getContent(0L);
    }

    @PostMapping("/import")
    public void importContent(@RequestBody MultipartFile[] multipartFiles) {
        contentService.importContent(multipartFiles);
    }
}
