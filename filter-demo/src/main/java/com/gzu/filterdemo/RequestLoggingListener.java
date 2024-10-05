package com.gzu.filterdemo;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.http.HttpServletRequest;
import java.util.logging.Logger;
import java.util.logging.Level;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class RequestLoggingListener implements ServletRequestListener {

    private static final Logger LOGGER = Logger.getLogger(RequestLoggingListener.class.getName());

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        long startTime = System.currentTimeMillis();

        // 记录请求的开始时间和其他信息
        LOGGER.log(Level.INFO, "Request Start Time: {0}", startTime);
        request.setAttribute("startTime", startTime);
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();

        // 计算请求处理时间
        long processingTime = endTime - startTime;

        String remoteAddr = request.getRemoteAddr();
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        String queryString = request.getQueryString();
        String userAgent = request.getHeader("User-Agent");


        LOGGER.log(Level.INFO, "Request End Time: {0}", endTime);//请求时间
        LOGGER.log(Level.INFO, "Client IP: {0}", remoteAddr);  //客户端IP地址
        LOGGER.log(Level.INFO, "Request Method: {0}", method); //请求方法（GET, POST 等）
        LOGGER.log(Level.INFO, "Request URI: {0}", requestURI); //请求 URI
        LOGGER.log(Level.INFO, "Query String: {0}", queryString); //查询字符串（如果有）
        LOGGER.log(Level.INFO, "User-Agent: {0}", userAgent); //User-Agent
        LOGGER.log(Level.INFO, "Processing Time: {0}", processingTime);//请求处理时间（从请求开始到结束的时间）
    }
}
