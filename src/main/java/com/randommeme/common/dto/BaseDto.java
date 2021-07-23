package com.randommeme.common.dto;

import lombok.*;

import java.io.Serializable;

/**
 * 公共DTO父类
 *
 * @author luzhaofeng
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseDto implements Serializable {

    /**
     * 页码
     */
    private Integer pageNo = 1;

    /**
     * 每页数量
     */
    private Integer pageSize = 10;
}
