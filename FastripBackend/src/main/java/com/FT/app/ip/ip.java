package com.FT.app.ip;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ip {

    public static void main(String args[]) throws UnknownHostException {
        InetAddress ipAddress = InetAddress.getLocalHost();

        System.out.println("현재 아이피 : " + ipAddress.getHostAddress());
        System.out.println("현재 호스트명 : " + ipAddress.getHostName());
    }
}