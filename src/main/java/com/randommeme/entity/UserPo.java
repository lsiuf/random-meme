package com.randommeme.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 用户表 数据库实体类-必须与数据库一一对应
 *
 * @author lsivt
 * @date 2021-06-23 10:41:53
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("t_user")
public class UserPo {

    @TableId(value = "id")
    private Long id;
    /**
     * 账号
     */
    @TableField("account")
    private String account;
    /**
     * 密码
     */
    @TableField("password")
    private String password;
    /**
     * 昵称
     */
    @TableField("user_name")
    private String userName;
    /**
     * 头像
     */
    @TableField("user_img")
    private String userImg;
    /**
     * 手机号
     */
    @TableField("mobile")
    private String mobile;
    /**
     * 用户类型(-1:管理员 0:普通用户)
     */
    @TableField("type")
    private Integer type;
    /**
     * 状态(0:禁用 1:可用)
     */
    @TableField("status")
    private Integer status;
}
