package kr.co.rland.web.socket.config;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WSData { //websocketdata
    private int type;
    private String content;
}
