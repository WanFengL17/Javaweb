package com.gzu.filterdemo;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class VisitorCounterListener implements ServletContextListener, HttpSessionListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        ctx.setAttribute("totalVisits", 0);
        ctx.setAttribute("currentOnline", 0);
        System.out.println("Visitor counter initialized.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 清理工作，如果需要的话
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext ctx = se.getSession().getServletContext();
        int totalVisits = (int) ctx.getAttribute("totalVisits");
        int currentOnline = (int) ctx.getAttribute("currentOnline");

        ctx.setAttribute("totalVisits", totalVisits + 1);
        ctx.setAttribute("currentOnline", currentOnline + 1);

        System.out.println("New session created. Total visits: " + (totalVisits + 1) + ", Current online: " + (currentOnline + 1));
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext ctx = se.getSession().getServletContext();
        int currentOnline = (int) ctx.getAttribute("currentOnline");

        ctx.setAttribute("currentOnline", currentOnline - 1);

        System.out.println("Session destroyed. Current online: " + (currentOnline - 1));
    }
}
