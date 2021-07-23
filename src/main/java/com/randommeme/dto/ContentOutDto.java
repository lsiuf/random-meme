package com.randommeme.dto;

import lombok.*;

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
@EqualsAndHashCode
public class ContentOutDto implements Serializable {

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
     * 内容文件名称
     */
    private String fileName;

    /**
     * 来源作者
     */
    private String author;

    /**
     * 阅读数
     */
    private Integer view;

    /**
     * 推荐数
     */
    private Integer recommend;

    /**
     * 不推荐数
     */
    private Integer notRecommend;

    /**
     * 用户推荐状态
     * 0:不推荐 1:推荐 null:未操作
     */
    private Integer userRecommendType;

    /**
     * 分类编号
     */
    private String classifyCode;

    /**
     * 分类名称
     */
    private String classifyName;

    /**
     * 内容类型(1:图片 2:GIF 3:视频)
     */
    private Integer type;

    /**
     * 状态(0:下架 1:上架)
     */
    private Integer status;
}
