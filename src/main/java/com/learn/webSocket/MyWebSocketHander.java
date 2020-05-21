package com.learn.webSocket;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSONObject;
import com.learn.model.User;
import com.learn.model.classInfo;

@Component
@Service
public class MyWebSocketHander implements WebSocketHandler{
	private static final Logger logger = org.apache.log4j.Logger.getLogger(MyWebSocketHander.class);
    private Map<String,WebSocketSession> usersMap = new ConcurrentHashMap<String,WebSocketSession>();
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus arg1) throws Exception {
		 logger.info("连接关闭");
		User adminuser = (User) session.getHandshakeAttributes().get("socketUser");
		usersMap.remove(adminuser.getUserid().toString());
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("建立socket连接");
		User adminuser = (User) session.getHandshakeAttributes().get("socketUser");
		if (usersMap.get(adminuser.getUserid().toString()) == null) {
			usersMap.put(adminuser.getUserid().toString(), session);
		}
		
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> webSocketMessage) throws Exception {
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		 logger.error("连接出现错误",exception);
		 if(session.isOpen()){
	            session.close();
	        }
		 User adminuser = (User) session.getHandshakeAttributes().get("socketUser");
		 usersMap.remove(adminuser.getUserid().toString());
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

	public void sendMessageToUsers(String userId,TextMessage message){
        Set<Map.Entry<String, WebSocketSession>> entrySet = usersMap.entrySet();
        for(Map.Entry<String, WebSocketSession> entry : entrySet){
            String userid = "";
            try{
            	userid = entry.getKey();
            if(userid==null||userid.equals(userId)){
                continue;
            }
            WebSocketSession session = entry.getValue();
            session.sendMessage((WebSocketMessage<?>) message);
            }catch(Exception e){
                logger.error("发送信息给"+userid+"失败",e);
            }
        }
    }
	
	public void sendMessageToUser(String userId, TextMessage message)
			throws IOException {
		WebSocketSession session = usersMap.get(userId);
		if (session != null && session.isOpen()) {
			session.sendMessage((WebSocketMessage<?>) message);
		}
	}

}
