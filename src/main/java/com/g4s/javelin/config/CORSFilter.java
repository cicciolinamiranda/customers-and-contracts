package com.g4s.javelin.config;

import java.io.IOException;
import java.io.NotActiveException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.g4s.javelin.enums.CORSHeadersEnum;

public class CORSFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(CORSFilter.class.getName());

    protected boolean allowCredentials = true;
    protected String allowedMethods = "GET, POST, PUT, DELETE, OPTIONS, HEAD";
    protected String allowedHeaders = "ORIGIN, CONTENT-TYPE, ACCEPT, AUTHORIZATION";
    protected String exposedHeaders;
    protected final int corsMaxAge = 600;
    protected Set<String> allowedOrigins = new HashSet<>();

    public CORSFilter() {
        allowedOrigins.add("http://localhost:8081");
    }

    private void preflight(final HttpServletRequest requestContext, final HttpServletResponse responseContext, final String origin) {
        checkOrigin(requestContext, responseContext, origin);
        String requestMethods = requestContext.getHeader(CORSHeadersEnum.ACCESS_CONTROL_REQUEST_METHOD.getValue());
        if (requestMethods != null) {
            if (allowedMethods != null) {
                requestMethods = this.allowedMethods;
            }
            responseContext.setHeader(CORSHeadersEnum.ACCESS_CONTROL_ALLOW_METHODS.getValue(), requestMethods);
        }
        String allowHeaders = requestContext.getHeader(CORSHeadersEnum.ACCESS_CONTROL_REQUEST_HEADERS.getValue());
        if (allowHeaders != null) {
            if (allowedHeaders != null) {
                allowHeaders = this.allowedHeaders;
            }
            responseContext.setHeader(CORSHeadersEnum.ACCESS_CONTROL_ALLOW_HEADERS.getValue(), allowHeaders);
        }
        if (corsMaxAge > -1) {
            responseContext.setHeader(CORSHeadersEnum.ACCESS_CONTROL_MAX_AGE.getValue(), String.valueOf(corsMaxAge));
        }

    }

    private void checkOrigin(final HttpServletRequest requestContext, final HttpServletResponse responseContext, final String origin) {
        if (origin != null && !allowedOrigins.contains("*") && !allowedOrigins.contains(origin)) {
            throw new RuntimeException("Origin not allowed: " + origin);
        } else {
            responseContext.setStatus(HttpServletResponse.SC_OK);
            responseContext.setHeader(CORSHeadersEnum.ACCESS_CONTROL_ALLOW_ORIGIN.getValue(), origin);
            responseContext.setHeader(CORSHeadersEnum.ACCESS_CONTROL_ALLOW_CREDENTIALS.getValue(), Boolean.toString(allowCredentials));
            if (exposedHeaders != null) {
                responseContext.setHeader(CORSHeadersEnum.ACCESS_CONTROL_EXPOSE_HEADERS.getValue(), exposedHeaders);
            }
        }
    }

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response,
            final FilterChain chain) throws IOException, ServletException {
        HttpServletRequest requestContext = (HttpServletRequest) request;
        HttpServletResponse responseContext = (HttpServletResponse) response;
        try {
            String origin = requestContext.getHeader("ORIGIN");
            if (requestContext.getMethod().equalsIgnoreCase("OPTIONS")) {
                LOGGER.info("Attempting to set pre-flight response headers.");
                preflight(requestContext, responseContext, origin);
            } else {
                checkOrigin(requestContext, responseContext, origin);
                LOGGER.info("Origin is allowed. Executing next filter.");
                chain.doFilter(request, response);
            }
        } catch (NotActiveException e) {
            LOGGER.warning(e.getMessage());
            responseContext.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @Override
    public void destroy() {

    }
}
