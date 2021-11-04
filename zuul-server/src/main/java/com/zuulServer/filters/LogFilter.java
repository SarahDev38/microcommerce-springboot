package com.zuulServer.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class LogFilter extends ZuulFilter {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean shouldFilter() {
		// doit-on utiliser le filtre ?
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		log.info("******* requête interceptée avec l'url : " + request.getRequestURL() + " ******");
		return null;
	}

	@Override
	public String filterType() {
		// "pre" or "post"
		return "pre";
	}

	@Override
	public int filterOrder() {
		// ordre dans l'exécution des filtres
		return 1;
	}

}
