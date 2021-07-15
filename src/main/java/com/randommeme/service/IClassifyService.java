package com.randommeme.service;

import com.github.pagehelper.PageInfo;
import com.randommeme.dto.ClassifyDto;
import com.randommeme.entity.ClassifyPo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 分类表  业务层访问接口
 *
 * @author lsivt
 * @date 2021-06-24 15:44:26
 */
public interface IClassifyService extends IService<ClassifyPo> {

    /**
     * 分页查询
     *
     * @param classifyDto
     * @return
     */
    PageInfo<ClassifyDto> page(ClassifyDto classifyDto);

    /**
     * 新增分类
     *
     * @param classifyDto
     */
    void insert(ClassifyDto classifyDto);

    /**
     * 更新分类
     *
     * @param classifyDto
     */
    void update(ClassifyDto classifyDto);

    /**
     * 更新最值
     *
     * @param classifyCode
     */
    void updateMinAndMaxId(String classifyCode);
}