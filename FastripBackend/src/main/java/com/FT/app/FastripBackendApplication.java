package com.FT.app;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FastripBackendApplication {


	    public static void main(String args[]) throws UnknownHostException {
			SpringApplication.run(FastripBackendApplication.class, args);
	        InetAddress ipAddress = InetAddress.getLocalHost();

	        System.out.println("현재 아이피 : " + ipAddress.getHostAddress());
	        System.out.println("현재 호스트명 : " + ipAddress.getHostName());
	    }
	

}
