package com.randommeme.common.constant;

/**
 * 内容类型枚举
 */
public enum ContentTypeEnum {
    /**
     * 图片
     */
    IMG(1, "img"),
    /**
     * JPG图片
     */
    JPG(IMG.getCode(), "jpg"),
    /**
     * PNG图片
     */
    PNG(IMG.getCode(), "png"),
    /**
     * GIF动态图
     */
    GIF(2, "gif"),
    /**
     * 视频
     */
    VIDEO(3, "mp4"),
    ;

    private Integer code;
    private String type;

    ContentTypeEnum(Integer code, String type) {
        this.code = code;
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public static ContentTypeEnum getInstance(String type) {
        for (ContentTypeEnum value : ContentTypeEnum.values()) {
            if (value.getType().equals(type)) {
                return value;
            }
        }
        return null;
    }
}
