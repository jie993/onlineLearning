package com.learn.utils;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*"); // ����������ַ����������
		response.addHeader("Access-Control-Allow-Methods", "REQUEST,GET,POST,PUT,DELETE,PATCH,HEAD");// ��������󷽷�
		response.addHeader("Access-Control-Allow-Headers", "Content-Type"); // ,X-Requested-With,auth_token
		response.addHeader("Access-Control-Max-Age", "1800");// 30 min
		filterChain.doFilter(request, response);
	}

}
