package com.randommeme.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.randommeme.common.entity.BasePo;
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
public class ContentPo extends BasePo<ContentPo> {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 内容编号
     */
    @TableField("content_code")
    private String contentCode;
    /**
     * 内容文件名称
     */
    @TableField("file_name")
    private String fileName;
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
     * 分类编号
     */
    @TableField("classify_code")
    private String classifyCode;
    /**
     * 内容类型(1:图片 2:GIF 3:视频)
     */
    @TableField("type")
    private Integer type;
    /**
     * 状态(0:下架 1:上架)
     */
    @TableField("status")
    private Integer status;
}
