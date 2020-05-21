package com.learn.utils;

import java.awt.List;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.learn.model.User;

import net.sf.json.JSONArray;


public class ResponseData {
	private final String message;
    private final int code;
    private final Map<String, Object> data = new HashMap<String, Object>();
    
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public ResponseData putDataValue(String key, Object value) {
        data.put(key, value);
        return this;
    }
    
    public ResponseData putDataValue(Object obj) {

        if (obj == null) {
            return null;
        }
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    data.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
    
    private ResponseData(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseData ok() {
        return new ResponseData(20000, "访问成功");
    }

    public static ResponseData notFound() {
        return new ResponseData(404, "服务器丢失");
    }

    public static ResponseData badRequest() {
        return new ResponseData(400, "服务器错误");
    }

    public static ResponseData forbidden() {
        return new ResponseData(403, "系统错误");
    }

    public static ResponseData unauthorized() {
        return new ResponseData(401, "未经授权");
    }
    
    public static ResponseData serverInternalError() {
        return new ResponseData(500, "服务器内部错误");
    }
    
    public static ResponseData customerError(String message) {
        return new ResponseData(1001, message);
    }
    
    public static ResponseData illegalToken(String message) {
        return new ResponseData(50008, message);
    }
    
    public static ResponseData tokenExpired(String message) {
        return new ResponseData(50014, message);
    }
    public static void main(String[] args) {
    	
    	ArrayList<User> userlist = new ArrayList<>();
    	
		User user = new User();
		User user1 = new User();
		user.setAvatar("dsaasd");
		user.setEmail("dsada");
		user1.setAvatar("dsaasd");
		user1.setEmail("dsada");
		
		userlist.add(user);
		userlist.add(user1);
		ResponseData responseData = ResponseData.ok();
		JSONArray ejson = JSONArray.fromObject(userlist);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("data", ejson);
		System.out.println(data);
		System.out.println(userlist);
	}
}
