package com.tanjunchen.util;

import java.net.Inet4Address;

public class IPUtil {
    public static String getIp() {
        try {
            return Inet4Address.getLocalHost().getHostAddress();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "[ can`t get ip ]";
    }
}
