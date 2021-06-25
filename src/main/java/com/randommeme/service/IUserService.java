package com.randommeme.service;

import com.randommeme.dto.UserDto;
import com.randommeme.entity.UserPo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户表  业务层访问接口
 *
 * @author lsivt
 * @date 2021-06-23 10:41:53
 */
public interface IUserService extends IService<UserPo> {

    /**
     * 注册
     *
     * @param account
     * @param password
     * @param mobile
     * @param smsCode
     */
    void register(String account, String password, String mobile, String smsCode);

    /**
     * 发送验证码
     *
     * @param mobile
     */
    void sendSmsCode(String mobile);

    /**
     * 登录
     *
     * @param account
     * @param password
     * @return
     */
    UserDto login(String account, String password);

    /**
     * 根据ID查询
     *
     * @param id
     */
    void getById(String id);
}