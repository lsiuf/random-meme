package com.randommeme.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;

/**
 * 内容表 最值对象
 *
 * @author lsivt
 * @date 2021-06-24 15:44:26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ContentExtremeValuePo {

    /**
     * 最小主键
     */
    @TableField("min_id")
    private Long minId;

    /**
     * 最大主键
     */
    @TableField("max_id")
    private Long maxId;
}
