package com.randommeme.common.constant;

/**
 * 公共状态枚举
 */
public enum CommonStatusEnum {
    /**
     * 可用
     */
    USABLE(1),
    /**
     * 禁用
     */
    DISABLED(0),
    ;

    private Integer code;

    CommonStatusEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static CommonStatusEnum getInstance(Integer code) {
        if (code == null) {
            return null;
        }
        for (CommonStatusEnum value : CommonStatusEnum.values()) {
            if (value.equals(code)) {
                return value;
            }
        }
        return null;
    }
}
