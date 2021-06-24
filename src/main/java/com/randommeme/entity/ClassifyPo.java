package com.randommeme.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
public class ClassifyPo {

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
}
