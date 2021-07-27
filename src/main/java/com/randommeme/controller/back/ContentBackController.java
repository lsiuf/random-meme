package com.randommeme.controller.back;

import com.github.pagehelper.PageInfo;
import com.randommeme.dto.ContentDto;
import com.randommeme.dto.ContentOutDto;
import com.randommeme.service.IContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @PostMapping("/page")
    public PageInfo<ContentOutDto> page(@RequestBody ContentDto param) {
        PageInfo<ContentOutDto> result = contentService.page(param);
        return result;
    }

    @PostMapping("/update")
    public void update(@RequestBody ContentDto param) {
        contentService.update(param);
    }

    @PostMapping("/batchUpdateClassify")
    public void batchUpdateClassify(@RequestBody List<Long> ids, @RequestParam("classifyCode") String classifyCode) {
        contentService.batchUpdateClassify(ids, classifyCode);
        return;
    }

    @PostMapping("/import")
    public void importContent(@RequestBody MultipartFile[] multipartFiles) {
        contentService.importContent(multipartFiles);
    }
}
