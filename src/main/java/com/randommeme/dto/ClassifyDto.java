package com.randommeme.dto;

import lombok.*;

import java.util.Date;
import java.io.Serializable;

/**
 * 分类表 业务调用传播实体类入与出
 *
 * @author lsivt
 * @date 2021-06-24 15:44:26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ClassifyDto implements Serializable {

    private static final long serialVersionUID = -1L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 分类名称
     */
    private String classifyName;

    /**
     * 分类编码
     */
    private String classifyCode;

    /**
     * 最小内容主键
     */
    private Long minContentId;

    /**
     * 最大内容主键
     */
    private Long maxContentId;

    /**
     * 分类内容数量
     */
    private Long contentTotal;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
