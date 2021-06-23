package com.randommeme.dto;

import lombok.*;

import java.util.Date;
import java.io.Serializable;

/**
 * 评论表 业务调用传播实体类入与出
 *
 * @author lsivt
 * @date 2021-06-23 10:41:53
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CommentDto implements Serializable {

    private static final long serialVersionUID = -1L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 内容主键
     */
    private Long contentId;

    /**
     * 内容编号
     */
    private String contentCode;

    /**
     * 用户主键
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userImg;

    /**
     * 评论
     */
    private String comment;

    /**
     * 评论排序
     */
    private Integer rank;

    /**
     * 状态(0:下架 1:上架)
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
