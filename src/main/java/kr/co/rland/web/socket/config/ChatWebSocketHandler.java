package kr.co.rland.web.socket.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    List<WebSocketSession> users = new ArrayList<>();
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        users.add(session);
        System.out.println("connected from "+session.getRemoteAddress());
        //session.sendMessage(new TextMessage("welcome!!"));
    }

    
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();
        WSData data = new Gson().fromJson(msg, WSData.class);
        data.getType();
        System.out.println();

        //클라이언트에 대한 정보가 누적될 수 있는 공간 필요
        //1. 세션데이터 2. 클라이언트 데이터 -> WSUser

        //1.세션 데이터
        for(WebSocketSession s : users)
            s.sendMessage(new TextMessage(message.getPayload()))
        
        
;        
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        users.remove(session);
        System.out.println("closed from "+session.getRemoteAddress());

    }



}
