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
        Long userIP = null;
        String clientIP = ServletUtil.getClientIP(httpServletRequest);
        if (StrUtil.isNotBlank(clientIP)) {
            if (Validator.isIpv4(clientIP)) {
                userIP = NetUtil.ipv4ToLong(clientIP);
            }
            if (Validator.isIpv6(clientIP)) {
                userIP = NetUtil.ipv6ToBitInteger(clientIP).longValue();
            }
        }
        return userIP;
    }
}
