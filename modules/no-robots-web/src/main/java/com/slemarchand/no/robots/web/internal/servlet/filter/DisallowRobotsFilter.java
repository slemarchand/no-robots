package com.slemarchand.no.robots.web.internal.servlet.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * Override /robots.txt to disallow all robots
 *
 * @author Sébastien Le Marchand
 */
@Component(
	immediate = true,
	property = {
		"before-filter=Absolute Redirects Filter", "servlet-context-name=",
		"dispatcher=REQUEST", "servlet-filter-name=Disallow Robots",
		"url-pattern=/robots.txt"
	},
	service = Filter.class
)
public class DisallowRobotsFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(
			ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {

		HttpServletResponse httpServletResponse = (HttpServletResponse)response;

		PrintWriter writer = httpServletResponse.getWriter();

		writer.write(_CONTENT);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	private static final String _CONTENT = "User-agent: *\nDisallow: /";

}