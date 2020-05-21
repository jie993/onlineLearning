package com.learn.webSocket;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.alibaba.fastjson.JSONObject;
import com.learn.model.User;
import com.learn.utils.JWT;
import com.learn.utils.ResponseData;

public class WebSocketInterceptor implements HandshakeInterceptor{

	@Override
	public void afterHandshake(ServerHttpRequest arg0, ServerHttpResponse arg1, WebSocketHandler arg2, Exception arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler arg2,
			Map<String, Object> attributes) throws Exception {
		HttpServletRequest servletRequest = ((ServletServerHttpRequest)serverHttpRequest).getServletRequest();
		HttpServletResponse servletResponse = ((ServletServerHttpResponse)serverHttpResponse).getServletResponse();
		String cookie = servletRequest.getHeader("Cookie");
		String str1=cookie.substring(0, cookie.indexOf("="));
		String token=cookie.substring(str1.length()+1, cookie.length());
		if (null != token) {
			// 获取token中的实体类信息
			User adminuser = JWT.unsign(token, User.class);
			// 判断token是否合法
			if (null != adminuser) {
				attributes.put("socketUser", adminuser);
				return true;
				}else {
					// 这里的ResponseData是包装的json工具类
					ResponseData responseData = ResponseData.tokenExpired("Token已过期");
					responseMessage(servletResponse, servletResponse.getWriter(), responseData);
					return false;
				}
			}else {
			ResponseData responseData = ResponseData.illegalToken("无Token");
			responseMessage(servletResponse, servletResponse.getWriter(), responseData);
			return false;
		}
	}
	// 请求不通过，返回错误信息给客户端
		private void responseMessage(HttpServletResponse response, PrintWriter out, ResponseData responseData) {
			response.setContentType("application/json; charset=utf-8");
			response.setCharacterEncoding("utf-8");
			String json = JSONObject.toJSONString(responseData);
			out.print(json.toString());
			out.flush();
			out.close();
		}
}
