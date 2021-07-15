package com.randommeme.common.util;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * token操作工具类
 *
 * @author luzhaofeng
 */
public class TokenUtils {

    /**
     * 获取请求用户IP
     *
     * @param httpServletRequest
     * @return
     */
    public static Long getUserIP(HttpServletRequest httpServletRequest) {
        String clientIP = ServletUtil.getClientIP(httpServletRequest);
        if (StrUtil.isBlank(clientIP)) {
            return null;
        }
        if (Validator.isIpv4(clientIP)) {
            return NetUtil.ipv4ToLong(clientIP);
        }
        if (Validator.isIpv6(clientIP)) {
            return NetUtil.ipv6ToBitInteger(clientIP).longValue();
        }
        return null;
    }
}
