package com.hz.lkyblog.web.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

import static org.apache.commons.lang3.CharEncoding.UTF_8;

@Component
public class CharsetFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //设置字符集编码
        request.setCharacterEncoding(UTF_8);
        response.setCharacterEncoding(UTF_8);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
