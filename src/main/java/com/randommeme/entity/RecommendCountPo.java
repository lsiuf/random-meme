package com.randommeme.entity;

import lombok.*;

/**
 * 推荐表 推荐数统计对象
 *
 * @author luzhaofeng
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RecommendCountPo {

    /**
     * 推荐数
     */
    private Integer recommend;

    /**
     * 不推荐数
     */
    private Integer notRecommend;
}
