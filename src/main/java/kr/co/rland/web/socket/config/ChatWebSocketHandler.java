package kr.co.rland.web.socket.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    List<WSUser> users = new ArrayList<>();
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        session.getPrincipal(); //사용자 정보를 가져올수있음 그러나 우리가 커스텀한 유저정보는 사용못함

        //WSUser user = new WSUser();
     
        //users.add(session);
        //System.out.println("connected from "+session.getRemoteAddress());
        //session.sendMessage(new TextMessage("welcome!!"));
    }

    
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        String msg = message.getPayload();
        WSData data = new Gson().fromJson(msg, WSData.class);
        
        //새로운 사용자가 올 경우만 처리
        if(data.getType()==1){ 
            WSUser user = new WSUser();
            user.setSession(session); 
            user.setUsername(data.getUsername()); //content가 username을 갖고 있음
            users.add(user);

            return;
        }

        //사용자 메시지를 처리
        for(WSUser user : users) {
            String payload = message.getPayload();
            
            //WSData sendData = new WSData();
            // sendData.setType(2);
            // sendData.setUsername(user.getUsername());
            // sendData.setContent(payload);
            //sendData.setContent(data.getContent());
            //String textMessage = new Gson().toJson(payload);
            String textMessage = payload;

            user.getSession().sendMessage(new TextMessage(textMessage));
        }

        //클라이언트에 대한 정보가 누적될 수 있는 공간 필요
        //1. 세션데이터  2. 클라이언트 데이터 -> WSUser

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //users.remove(session);
        System.out.println("closed from "+session.getRemoteAddress());

    }

}
