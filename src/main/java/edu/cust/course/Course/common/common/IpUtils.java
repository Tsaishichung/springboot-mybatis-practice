package edu.cust.course.Course.common.common;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

public class IpUtils {  
	/**获取服务请求ip地址*/
    public static String getIpAddr(HttpServletRequest request) {  
           String ip = request.getHeader("X-Forwarded-For");  
           if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
               ip = request.getHeader("Proxy-Client-IP");  
           }  
           if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
               ip = request.getHeader("WL-Proxy-Client-IP");  
           }  
           if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
               ip = request.getHeader("HTTP_CLIENT_IP");  
           }  
           if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
               ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
           }  
           if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
               ip = request.getRemoteAddr();  
           }  
           return ip;  
       }  
    /**获取本机ip*/
    public static String getLocalIpAddress(){
    	String ip = null;
    	//String host = null;
    	try{
    	InetAddress ia = InetAddress.getLocalHost(); 
    	//host = ia.getHostName();//获取计算机名字
    	ip = ia.getHostAddress();//获取IP
	    } catch(UnknownHostException e) {
	    	e.printStackTrace();
    	}
    	return ip;
    }
}  