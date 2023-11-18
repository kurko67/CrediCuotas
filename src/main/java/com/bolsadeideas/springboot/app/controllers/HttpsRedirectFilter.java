package com.bolsadeideas.springboot.app.controllers;

import org.springframework.stereotype.Component;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class HttpsRedirectFilter implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (!httpRequest.isSecure()) {
            String httpsURL = "https://" + httpRequest.getServerName() + httpRequest.getRequestURI();
            if (httpRequest.getQueryString() != null) {
                httpsURL += "?" + httpRequest.getQueryString();
            }
            httpResponse.sendRedirect(httpsURL);
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed.
    }

    @Override
    public void destroy() {
        // Cleanup code, if needed.
    }



}
