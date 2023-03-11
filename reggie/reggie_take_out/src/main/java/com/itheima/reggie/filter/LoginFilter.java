package com.itheima.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.itheima.reggie.common.R;
import com.itheima.reggie.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "LoginFilter",urlPatterns = "/*")
public class LoginFilter implements Filter {
    public static final AntPathMatcher PATH_MATCHER=new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
//        1、获取本次请求的URI
        String url = request.getRequestURI();
        //        定义不需要处理的请求路径
        String[] urls=new String[]{"/employee/login","/employee/logout","/backend/**","/front/**"};
//        2、判断本次请求是否需要处理
        boolean result = check(urls,url);
//        3、如果不需要处理，则直接放行
        if(result){
            filterChain.doFilter(request,response);
            return;
        }
//        4、判断登录状态，如果已登录，则直接放行
        HttpSession session = request.getSession();
        Object employee = session.getAttribute("employee");
        if(employee!=null){
            ThreadLocalUtil.setCurrentId((Long) employee);
            filterChain.doFilter(request,response);
            return;
        }
//        5、如果未登录则返回未登录结果
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
    }

    public boolean check(String[] urls,String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if(match==true){
                return true;
            }
        }
        return  false;
    }
}
