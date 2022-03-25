package com.sawol.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		request.setCharacterEncoding("UTF-8");			// request에 인코딩 설정함
		chain.doFilter(request, response);			// 서블릿 컨테이너로 넘겨줌
	}
}
