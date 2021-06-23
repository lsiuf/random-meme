package com.randommeme.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 推荐表 数据库实体类-必须与数据库一一对应
 *
 * @author lsivt
 * @date 2021-06-23 10:41:53
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("t_recommend")
public class RecommendPo {

    @TableId(value = "id")
    private Long id;
    /**
     * 内容主键
     */
    @TableField("content_id")
    private Long contentId;
    /**
     * 用户主键
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 是否推荐(0:不推荐 1:推荐)
     */
    @TableField("recommend")
    private Integer recommend;
}
