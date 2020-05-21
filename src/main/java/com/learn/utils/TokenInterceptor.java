package com.learn.utils;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSONObject;
import com.learn.model.User;

public class TokenInterceptor implements HandlerInterceptor {
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception arg3) throws Exception {
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView model)
			throws Exception {
	}

	// ����ÿ������
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.setCharacterEncoding("utf-8");
		// ��ȡ����ͷ�е�Token��Ϣ
		String token = request.getHeader("X-Token");
		System.out.println(token);
		// �ж�token�Ƿ����
		if (null != token) {
			// ��ȡtoken�е�ʵ������Ϣ
			User adminuser = JWT.unsign(token, User.class);
			// ��ȡ����ͷ���û�id��Ϣ
			/*String userId = request.getHeader("userId");*/
			// �ж�token�Ƿ�Ϸ�
			if (null != adminuser) {
				return true;
				}else {
					// �����ResponseData�ǰ�װ��json������
					ResponseData responseData = ResponseData.tokenExpired("Token�ѹ���");
					responseMessage(response, response.getWriter(), responseData);
					return false;
				}
			}else {
			ResponseData responseData = ResponseData.illegalToken("��Token");
			responseMessage(response, response.getWriter(), responseData);
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