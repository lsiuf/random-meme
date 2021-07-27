package com.randommeme.service;

import com.github.pagehelper.PageInfo;
import com.randommeme.dto.ContentDto;
import com.randommeme.dto.ContentOutDto;
import com.randommeme.entity.ContentExtremeValuePo;
import com.randommeme.entity.ContentPo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 内容表  业务层访问接口
 *
 * @author lsivt
 * @date 2021-06-23 10:41:53
 */
public interface IContentService extends IService<ContentPo> {

    /**
     * 分页查询
     *
     * @param param
     * @return
     */
    PageInfo<ContentOutDto> page(ContentDto param);

    /**
     * 获取一个内容
     *
     * @param userId
     * @return
     */
    ContentOutDto getContent(Long userId);

    /**
     * 查询主键最值
     * min(id)、max(id)
     *
     * @param classifyCode
     * @return
     */
    ContentExtremeValuePo getMinAndMaxId(String classifyCode);

    /**
     * 批量导入内容
     */
    void importContent(MultipartFile[] multipartFile);

    /**
     * 更新内容
     *
     * @param contentDto
     */
    void update(ContentDto contentDto);

    /**
     * 批量更新分类
     *
     * @param ids
     * @param classifyCode
     */
    void batchUpdateClassify(List<Long> ids, String classifyCode);

    /**
     * 删除内容
     *
     * @param ids
     */
    void remove(List<Long> ids);
}