package com.randommeme.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.randommeme.dto.ContentDto;
import com.randommeme.entity.ClassifyPo;
import com.randommeme.entity.ContentExtremeValuePo;
import com.randommeme.service.IClassifyService;
import com.randommeme.service.IContentService;
import com.randommeme.dao.IContentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import com.randommeme.entity.ContentPo;
import com.randommeme.convert.ContentConvert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 内容表  业务层访问接口实现类
 *
 * @author lsivt
 * @date 2021-06-23 10:41:53
 */
@Slf4j
@Service
public class ContentServiceImpl extends ServiceImpl<IContentDao, ContentPo> implements IContentService {

    @Autowired
    private ContentConvert contentConvert;
    @Autowired
    private IClassifyService classifyService;

    @Override
    public ContentDto getContent(Long userId) {
        List<ClassifyPo> classifyPos = classifyService.list(Wrappers.lambdaQuery(ClassifyPo.class));
        LambdaQueryWrapper<ContentPo> contentQuery = Wrappers.lambdaQuery(ContentPo.class);
        if (CollectionUtil.isNotEmpty(classifyPos)) {
            ClassifyPo classifyPo = RandomUtil.randomEle(classifyPos);
            log.info("getContent-info: random classify. name:{}; code:{}", classifyPo.getClassifyName(), classifyPo.getClassifyCode());
            contentQuery.eq(ContentPo::getClassifyCode, classifyPo.getClassifyCode());
            if (classifyPo.getMinContentId() != null && classifyPo.getMaxContentId() != null) {
                Long randomId = RandomUtil.randomLong(classifyPo.getMinContentId(), classifyPo.getMaxContentId()) + classifyPo.getMinContentId();
                contentQuery.ge(ContentPo::getId, randomId);
            }
        } else {
            ContentExtremeValuePo contentExtremeValuePo = getBaseMapper().getMinAndMaxId(null);
            if (contentExtremeValuePo != null) {
                Long randomId = RandomUtil.randomLong(contentExtremeValuePo.getMinId(), contentExtremeValuePo.getMaxId()) + contentExtremeValuePo.getMinId();
                contentQuery.ge(ContentPo::getId, randomId);
            }
        }
        ContentPo contentPo = getOne(contentQuery, false);
        return contentConvert.poToDto(contentPo);
    }

    @Override
    public ContentExtremeValuePo getMinAndMaxId(String classifyCode) {
        return this.getBaseMapper().getMinAndMaxId(classifyCode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importContent(MultipartFile[] multipartFiles) {
        List<ContentPo> poList = new ArrayList<>();
        InputStream inputStream = null;
        BufferedOutputStream outputStream = null;
        try {
            for (MultipartFile multipartFile : multipartFiles) {
                inputStream = multipartFile.getInputStream();
                String type = FileTypeUtil.getType(inputStream);
                //TODO
                String fileName = "D:\\testfile\\" + IdUtil.fastSimpleUUID() + "." + type;
                outputStream = FileUtil.getOutputStream(fileName);
                outputStream.write(multipartFile.getBytes());
                IoUtil.close(inputStream);
                IoUtil.close(outputStream);

                ContentPo po = new ContentPo();
                po.setId(IdWorker.getId());
                po.setContentCode(RandomUtil.randomStringUpper(5) + System.currentTimeMillis());
                po.setContentUrl(fileName);
                poList.add(po);
            }
            saveBatch(poList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IoUtil.close(inputStream);
            IoUtil.close(outputStream);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ContentDto contentDto) {
        ContentPo po = getById(contentDto.getId());
        if (po == null) {
            return;
        }
        ContentPo updatePo = new ContentPo();
        updatePo.setAuthor(contentDto.getAuthor());
        updatePo.setClassifyCode(contentDto.getClassifyCode());
        updatePo.setType(contentDto.getType());
        updatePo.setStatus(contentDto.getStatus());
        updateById(updatePo);

        classifyService.updateMinAndMaxId(contentDto.getClassifyCode());
    }
}
