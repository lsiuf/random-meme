package com.randommeme.entity;

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
    private Long minId;

    /**
     * 最大主键
     */
    private Long maxId;
}
