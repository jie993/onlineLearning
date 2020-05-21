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
			// ��ȡtoken�е�ʵ������Ϣ
			User adminuser = JWT.unsign(token, User.class);
			// �ж�token�Ƿ�Ϸ�
			if (null != adminuser) {
				attributes.put("socketUser", adminuser);
				return true;
				}else {
					// �����ResponseData�ǰ�װ��json������
					ResponseData responseData = ResponseData.tokenExpired("Token�ѹ���");
					responseMessage(servletResponse, servletResponse.getWriter(), responseData);
					return false;
				}
			}else {
			ResponseData responseData = ResponseData.illegalToken("��Token");
			responseMessage(servletResponse, servletResponse.getWriter(), responseData);
			return false;
		}
	}
	// ����ͨ�������ش�����Ϣ���ͻ���
		private void responseMessage(HttpServletResponse response, PrintWriter out, ResponseData responseData) {
			response.setContentType("application/json; charset=utf-8");
			response.setCharacterEncoding("utf-8");
			String json = JSONObject.toJSONString(responseData);
			out.print(json.toString());
			out.flush();
			out.close();
		}
}
