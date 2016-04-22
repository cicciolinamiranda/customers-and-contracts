package com.g4s.javelin.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Darryl Ong
 * @since April 22, 2016
 * */
public final class ServletRequestUtil {
    private ServletRequestUtil() {
    }

    public static String extractIpAddress(final HttpServletRequest request) {
        String ipAddress = null;
        if (request != null) {
            ipAddress = request.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }
        }

        return ipAddress;
    }
}
