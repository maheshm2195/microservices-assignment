package com.nagarro.microservices.assignment.zuulapigateway.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class ZuulLoggingFilter extends ZuulFilter {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Object run() {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		logger.info("*****************************************");
		logger.info("********************request received: request -> {} request url -> {}", request, request.getRequestURI());
		logger.info("*****************************************");
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public String filterType() {
		return "post";
	}

}