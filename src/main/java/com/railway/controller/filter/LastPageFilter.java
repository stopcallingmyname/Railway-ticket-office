package com.railway.controller.filter;

import com.railway.controller.command.RequestAttribute;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * The Last page filter. It set attribute with last page URL.
 */
@WebFilter(urlPatterns = { "/*" })
public class LastPageFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String pageFrom = httpServletRequest.getHeader("referer");
        request.setAttribute(RequestAttribute.PAGE_FROM, pageFrom);
        chain.doFilter(request, response);
    }
}
