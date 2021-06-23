package com.randommeme.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 内容表 数据库实体类-必须与数据库一一对应
 *
 * @author lsivt
 * @date 2021-06-23 10:41:53
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("t_content")
public class ContentPo {

    @TableId(value = "id")
    private Long id;
    /**
     * 内容编号
     */
    @TableField("content_code")
    private String contentCode;
    /**
     * 内容地址
     */
    @TableField("content_url")
    private String contentUrl;
    /**
     * 来源作者
     */
    @TableField("author")
    private String author;
    /**
     * 阅读数
     */
    @TableField("view")
    private Integer view;
    /**
     * 评论数
     */
    @TableField("comment")
    private Integer comment;
    /**
     * 推荐数
     */
    @TableField("recommend")
    private Integer recommend;
    /**
     * 状态(0:下架 1:上架)
     */
    @TableField("status")
    private Integer status;
}
