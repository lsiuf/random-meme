package com.randommeme.controller.back;

import com.github.pagehelper.PageInfo;
import com.randommeme.dto.ClassifyDto;
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
@RequestMapping("/admin/classify")
public class ClassifyBackController {

    @Autowired
    private IClassifyService classifyService;

    @PostMapping("/page")
    public PageInfo<ClassifyDto> page(@RequestBody ClassifyDto param) {
        return classifyService.page(param);
    }

    @PostMapping("/insert")
    public void insert(String classifyName) {
        classifyService.insert(classifyName);
    }

    @PostMapping("/update")
    public void update(@RequestBody ClassifyDto param) {
        classifyService.update(param);
    }
}
