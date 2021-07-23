package com.randommeme.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.randommeme.dao.IClassifyDao;
import com.randommeme.dto.ClassifyDto;
import com.randommeme.entity.ContentExtremeValuePo;
import com.randommeme.service.IClassifyService;
import com.randommeme.service.IContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.randommeme.entity.ClassifyPo;
import com.randommeme.convert.ClassifyConvert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 分类表  业务层访问接口实现类
 *
 * @author lsivt
 * @date 2021-06-24 15:44:26
 */
@Slf4j
@Service
public class ClassifyServiceImpl extends ServiceImpl<IClassifyDao, ClassifyPo> implements IClassifyService {

    @Autowired
    private ClassifyConvert classifyConvert;
    @Autowired
    @Lazy
    private IContentService contentService;

    @Override
    public PageInfo<ClassifyDto> page(ClassifyDto classifyDto) {
        LambdaQueryWrapper<ClassifyPo> query = Wrappers.lambdaQuery(ClassifyPo.class)
                .eq(classifyDto.getId() != null, ClassifyPo::getId, classifyDto.getId())
                .eq(StrUtil.isNotBlank(classifyDto.getClassifyCode()), ClassifyPo::getClassifyCode, classifyDto.getClassifyCode())
                .like(StrUtil.isNotBlank(classifyDto.getClassifyName()), ClassifyPo::getClassifyName, classifyDto.getClassifyName());
        PageInfo<ClassifyPo> pagePo = PageHelper.startPage(classifyDto.getPageNo(), classifyDto.getPageSize()).doSelectPageInfo(() -> {
            list(query);
        });
        return classifyConvert.pagePoToDto(pagePo);
    }

    @Override
    public void insert(String classifyName) {
        if (StrUtil.isBlank(classifyName)) {
            return;
        }
        ClassifyPo po = new ClassifyPo();
        po.setId(IdWorker.getId());
        po.setClassifyName(classifyName);
        po.setClassifyCode(RandomUtil.randomStringUpper(5));
        save(po);
    }

    @Override
    public void update(ClassifyDto classifyDto) {
        ClassifyPo po = getById(classifyDto.getId());
        if (po == null) {
            return;
        }
        if (StrUtil.isBlank(classifyDto.getClassifyName())) {
            return;
        }
        ClassifyPo updatePo = new ClassifyPo();
        updatePo.setId(classifyDto.getId());
        updatePo.setClassifyName(classifyDto.getClassifyName());
        updateById(updatePo);
    }

    @Override
    public void updateMinAndMaxId(String classifyCode) {
        ContentExtremeValuePo contentExtremeValuePo = contentService.getMinAndMaxId(classifyCode);
        if (contentExtremeValuePo != null) {
            LambdaUpdateWrapper<ClassifyPo> updateClassifyQuery = Wrappers.lambdaUpdate(ClassifyPo.class)
                    .eq(ClassifyPo::getClassifyCode, classifyCode)
                    .set(ClassifyPo::getMinContentId, contentExtremeValuePo.getMinId())
                    .set(ClassifyPo::getMaxContentId, contentExtremeValuePo.getMaxId());
            update(updateClassifyQuery);
        }
    }
}
