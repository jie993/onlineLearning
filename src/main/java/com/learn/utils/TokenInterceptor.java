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

	// 拦截每个请求
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.setCharacterEncoding("utf-8");
		// 读取请求头中的Token信息
		String token = request.getHeader("X-Token");
		System.out.println(token);
		// 判断token是否存在
		if (null != token) {
			// 获取token中的实体类信息
			User adminuser = JWT.unsign(token, User.class);
			// 获取请求头中用户id信息
			/*String userId = request.getHeader("userId");*/
			// 判断token是否合法
			if (null != adminuser) {
				return true;
				}else {
					// 这里的ResponseData是包装的json工具类
					ResponseData responseData = ResponseData.tokenExpired("Token已过期");
					responseMessage(response, response.getWriter(), responseData);
					return false;
				}
			}else {
			ResponseData responseData = ResponseData.illegalToken("无Token");
			responseMessage(response, response.getWriter(), responseData);
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