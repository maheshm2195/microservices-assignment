package com.nagarro.microservices.assignment.zuulapigateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class ZuulErrorFilter extends ZuulFilter {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private static final String FILTER_TYPE = "error";
    private static final String THROWABLE_KEY = "throwable";
    private static final int FILTER_ORDER = -1;

    @Override
    public String filterType() {
        return FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        final RequestContext context = RequestContext.getCurrentContext();
        final Object throwable = context.get(THROWABLE_KEY);

        if (throwable instanceof ZuulException) {
            final ZuulException zuulException = (ZuulException) throwable;
            logger.error("Zuul failure detected: " + zuulException.getMessage());

            // remove error code to prevent further error handling in follow up filters
            context.remove(THROWABLE_KEY);

            // populate context with new response values
            context.setResponseBody("Overriding Zuul Exception Body");
            context.getResponse().setContentType("application/json");
            // can set any error code as excepted
            context.setResponseStatusCode(503);
        }
        return null;
    }
}