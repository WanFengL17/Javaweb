package com.gzu.filterdemo;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")  // 应用到所有 URL 路径
public class LoginFilter implements Filter {

    // 排除列表，不需要登录就能访问的路径
    private static final String[] EXCLUDE_PATHS = {
            "/login", "/register", "/public"
    };

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化过滤器时可以进行一些配置工作
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 将ServletRequest转换为HttpServletRequest
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // 获取请求的URI
        String path = req.getRequestURI();

        // 检查当前请求路径是否在排除列表中
        if (isExcluded(path)) {
            // 如果在排除列表中，直接执行请求
            chain.doFilter(request, response);
        } else {
            // 如果不在排除列表中，检查用户是否已经登录
            HttpSession session = req.getSession(false);
            if (session != null && session.getAttribute("user") != null) {
                // 用户已登录，继续请求
                chain.doFilter(request, response);
            } else {
                // 用户未登录，重定向到登录页面
                res.sendRedirect(req.getContextPath() + "/login");
            }
        }
    }

    @Override
    public void destroy() {
        // 销毁过滤器时可以进行一些资源释放工作
    }

    /**
     * 检查请求路径是否在排除列表中
     *
     * @param path 请求路径
     * @return 如果在排除列表中返回true，否则返回false
     */
    private boolean isExcluded(String path) {
        for (String excludePath : EXCLUDE_PATHS) {
            if (path.startsWith(excludePath)) {
                return true;
            }
        }
        return false;
    }
}
