package kr.co.rland.web.socket.config;

import org.springframework.web.socket.WebSocketSession;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WSUser { //채팅을 위한 사용자에 대한 데이터, 나중에 리스트에 모아놓을 것
    
    private String username;
    private WebSocketSession session;

}
