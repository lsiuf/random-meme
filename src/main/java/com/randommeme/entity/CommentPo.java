package com.randommeme.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.randommeme.common.entity.BasePo;
import lombok.*;

/**
 * 评论表 数据库实体类
 *
 * @author lsivt
 * @date 2021-06-23 10:41:53
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("t_comment")
public class CommentPo extends BasePo<CommentPo> {

    @TableId(value = "id")
    private Long id;
    /**
     * 内容主键
     */
    @TableField("content_id")
    private Long contentId;
    /**
     * 内容编号
     */
    @TableField("content_code")
    private String contentCode;
    /**
     * 用户主键
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;
    /**
     * 用户头像
     */
    @TableField("user_img")
    private String userImg;
    /**
     * 评论
     */
    @TableField("comment")
    private String comment;
    /**
     * 评论排序
     */
    @TableField("rank")
    private Integer rank;
    /**
     * 状态(0:下架 1:上架)
     */
    @TableField("status")
    private Integer status;
}
