package kr.co.rland.web.socket.config;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WSData { //websocketdata
    private int type;
    private String username;
    private String content;
}
