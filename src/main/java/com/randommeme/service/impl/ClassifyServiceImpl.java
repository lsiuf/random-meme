package com.randommeme.service.impl;

import com.randommeme.dao.IClassifyDao;
import com.randommeme.service.IClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
