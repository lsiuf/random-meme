package com.randommeme.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.randommeme.common.constant.CommonStatusEnum;
import com.randommeme.common.constant.ContentTypeEnum;
import com.randommeme.dto.ContentDto;
import com.randommeme.dto.ContentOutDto;
import com.randommeme.entity.*;
import com.randommeme.service.IClassifyService;
import com.randommeme.service.IContentService;
import com.randommeme.dao.IContentDao;
import com.randommeme.service.IRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

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
    @Autowired
    @Lazy
    private IRecommendService recommendService;
    @Value(value = "${content-url}")
    private String contentUrl;

    @Override
    public PageInfo<ContentOutDto> page(ContentDto param) {
        LambdaQueryWrapper<ContentPo> query = Wrappers.lambdaQuery(ContentPo.class)
                .like(StrUtil.isNotBlank(param.getContentCode()), ContentPo::getContentCode, param.getContentCode())
                .like(StrUtil.isNotBlank(param.getClassifyCode()), ContentPo::getClassifyCode, param.getClassifyCode())
                .eq(param.getType() != null, ContentPo::getType, param.getType())
                .eq(param.getStatus() != null, ContentPo::getStatus, param.getStatus())
                .orderByAsc(ContentPo::getClassifyCode)
                .orderByDesc(ContentPo::getClassifyCode);
        PageInfo<ContentPo> pagePo = PageHelper.startPage(param.getPageNo(), param.getPageSize()).doSelectPageInfo(() -> {
            list(query);
        });
        PageInfo<ContentOutDto> pageDto = contentConvert.pagePoToOutDto(pagePo);
        pageDto.getList().forEach(dto -> {
            RecommendCountPo recommendCountPo = recommendService.recommendCount(dto.getId());
            dto.setRecommend(recommendCountPo.getRecommend());
            dto.setNotRecommend(recommendCountPo.getNotRecommend());
            ClassifyPo classifyPo = classifyService.getOne(Wrappers.lambdaQuery(ClassifyPo.class)
                    .eq(StrUtil.isNotBlank(dto.getContentCode()), ClassifyPo::getClassifyCode, dto.getClassifyCode()), false);
            if (classifyPo != null) {
                dto.setClassifyName(classifyPo.getClassifyName());
            }
        });
        return pageDto;
    }

    @Override
    public ContentOutDto getContent(Long userId) {
        LambdaQueryWrapper<ContentPo> contentQuery = Wrappers.lambdaQuery(ContentPo.class);
        Long randomId = null;

        //随机一个分类对象，如果存在则根据分类中的最值随机一个内容ID
        List<ClassifyPo> classifyPos = classifyService.list();
        if (CollectionUtil.isNotEmpty(classifyPos)) {
            ClassifyPo classifyPo = RandomUtil.randomEle(classifyPos);
            if (classifyPo.getMinContentId() != null && classifyPo.getMaxContentId() != null) {
                contentQuery.eq(ContentPo::getClassifyCode, classifyPo.getClassifyCode());
                randomId = getRandomId(classifyPo.getMinContentId(), classifyPo.getMaxContentId());
            }
        }

        //当分类对象随机内容ID不存在，查询内容全表获得最值并随机一个内容ID
        if (randomId == null) {
            ContentExtremeValuePo contentExtremeValuePo = getBaseMapper().getMinAndMaxId(null);
            if (contentExtremeValuePo != null) {
                randomId = getRandomId(contentExtremeValuePo.getMinId(), contentExtremeValuePo.getMaxId());
            }
        }


        //根据内容ID >= 随机ID查询
        contentQuery.ge(randomId != null, ContentPo::getId, randomId);
        contentQuery.eq(ContentPo::getStatus, CommonStatusEnum.USABLE.getCode());
        ContentPo contentPo = getOne(contentQuery, false);
        ContentOutDto contentDto = contentConvert.poToOutDto(contentPo);
        if (contentDto == null) {
            return contentDto;
        }
        contentDto.setRecommend(0);
        contentDto.setNotRecommend(0);

        // 更新内容阅读数
        update(Wrappers.lambdaUpdate(ContentPo.class)
                .eq(ContentPo::getId, contentPo.getId())
                .setSql("view = view + 1"));

        // 设置推荐数据
        List<RecommendPo> recommendPos = recommendService.list(Wrappers.lambdaQuery(RecommendPo.class)
                .eq(RecommendPo::getContentId, contentDto.getId()));
        if (CollectionUtil.isNotEmpty(recommendPos)) {
            Integer recommend = 0;
            Integer notrecommend = 0;
            Integer userRecommendType = null;
            for (RecommendPo recommendPo : recommendPos) {
                if (CommonStatusEnum.USABLE.getCode().equals(recommendPo.getRecommend())) {
                    recommend += 1;
                }
                if (CommonStatusEnum.DISABLED.getCode().equals(recommendPo.getRecommend())) {
                    notrecommend += 1;
                }
                // 判断当前内容用户是否已经操作
                if (userId.equals(recommendPo.getUserId())) {
                    userRecommendType = recommendPo.getRecommend();
                }
            }
            contentDto.setRecommend(recommend);
            contentDto.setNotRecommend(notrecommend);
            contentDto.setUserRecommendType(userRecommendType);
        }

        // 设置分类名称
        if (StrUtil.isNotBlank(contentPo.getClassifyCode())) {
            ClassifyPo classifyPo = classifyPos.stream().filter(classify -> classify.getClassifyCode().equals(contentPo.getClassifyCode())).findFirst().orElse(null);
            if (classifyPo != null) {
                contentDto.setClassifyName(classifyPo.getClassifyName());
            }
        }

        return contentDto;
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
                ContentTypeEnum contentTypeEnum = ContentTypeEnum.getInstance(type);
                if (contentTypeEnum == null) {
                    log.error("导入失败, 不支持类型[ {} ]", type);
                    continue;
                }
                String fileName = IdUtil.fastSimpleUUID() + "." + type;
                outputStream = FileUtil.getOutputStream(contentUrl + fileName);
                outputStream.write(multipartFile.getBytes());
                IoUtil.close(inputStream);
                IoUtil.close(outputStream);

                ContentPo po = new ContentPo();
                po.setContentCode(RandomUtil.randomStringUpper(5) + System.currentTimeMillis());
                po.setFileName(fileName);
                po.setType(contentTypeEnum.getCode());
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

        update(Wrappers.lambdaUpdate(ContentPo.class)
                .eq(ContentPo::getId, contentDto.getId())
                .set(StrUtil.isNotBlank(contentDto.getAuthor()), ContentPo::getAuthor, contentDto.getAuthor())
                .set(StrUtil.isNotBlank(contentDto.getClassifyCode()), ContentPo::getClassifyCode, contentDto.getClassifyCode())
                .set(contentDto.getType() != null, ContentPo::getType, contentDto.getType())
                .set(contentDto.getStatus() != null, ContentPo::getStatus, contentDto.getStatus()));

        if (StrUtil.isNotBlank(contentDto.getClassifyCode())) {
            classifyService.updateMinAndMaxId(contentDto.getClassifyCode());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchUpdateClassify(List<Long> ids, String classifyCode) {
        update(Wrappers.lambdaUpdate(ContentPo.class)
                .in(ContentPo::getId, ids)
                .set(ContentPo::getClassifyCode, classifyCode));
        classifyService.updateMinAndMaxId(classifyCode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(List<Long> ids) {
        removeByIds(ids);
        //TODO 删除文件
    }

    /**
     * 根据最小值和最大值范围随机出一个数
     *
     * @param minId
     * @param maxId
     * @return
     */
    private Long getRandomId(Long minId, Long maxId) {
        return RandomUtil.randomLong(minId, maxId + 1);
    }
}
