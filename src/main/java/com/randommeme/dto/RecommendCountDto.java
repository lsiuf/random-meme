package com.randommeme.dto;

import lombok.*;

import java.io.Serializable;

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
public class RecommendCountDto implements Serializable {

    /**
     * 推荐数
     */
    private Integer recommend;

    /**
     * 不推荐数
     */
    private Integer notRecommend;
}
