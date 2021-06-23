package com.randommeme.dto;

import lombok.*;

import java.util.Date;
import java.io.Serializable;

/**
 * 内容表 业务调用传播实体类入与出
 *
 * @author lsivt
 * @date 2021-06-23 10:41:53
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ContentDto implements Serializable {

    private static final long serialVersionUID = -1L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 内容编号
     */
    private String contentCode;

    /**
     * 内容地址
     */
    private String contentUrl;

    /**
     * 来源作者
     */
    private String author;

    /**
     * 阅读数
     */
    private Integer view;

    /**
     * 评论数
     */
    private Integer comment;

    /**
     * 推荐数
     */
    private Integer recommend;

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
