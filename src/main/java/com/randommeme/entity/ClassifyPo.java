package com.randommeme.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.randommeme.common.entity.BasePo;
import lombok.*;

/**
 * 分类表 数据库实体类-必须与数据库一一对应
 *
 * @author lsivt
 * @date 2021-06-24 15:44:26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("t_classify")
public class ClassifyPo extends BasePo<ClassifyPo> {

    @TableId(value = "id")
    private Long id;
    /**
     * 分类名称
     */
    @TableField("classify_name")
    private String classifyName;
    /**
     * 分类编码
     */
    @TableField("classify_code")
    private String classifyCode;
    /**
     * 最小内容主键
     */
    @TableField("min_content_id")
    private Long minContentId;
    /**
     * 最大内容主键
     */
    @TableField("max_content_id")
    private Long maxContentId;
    /**
     * 分类内容数量
     */
    @TableField("content_total")
    private Long contentTotal;
}
