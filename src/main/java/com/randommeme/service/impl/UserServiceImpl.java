package com.randommeme.service.impl;

import com.randommeme.dao.IUserDao;
import com.randommeme.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.randommeme.entity.UserPo;
import com.randommeme.convert.UserConvert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 用户表  业务层访问接口实现类
 *
 * @author lsivt
 * @date 2021-06-23 10:41:53
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<IUserDao, UserPo> implements IUserService {

    @Autowired
    private UserConvert userConvert;
}
