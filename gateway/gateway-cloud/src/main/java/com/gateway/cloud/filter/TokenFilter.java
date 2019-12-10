package com.gateway.cloud.filter;

import com.gateway.cloud.client.UserCenterClient;
import com.gateway.cloud.common.Constants;
import com.gateway.cloud.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;


/**
 * token过滤器
 *
 * @Author: xujiping
 * @Date: 2019年9月5日 0005 上午 11:36:45
 * @Version 1.0
 */
@Slf4j
@Component
public class TokenFilter implements GlobalFilter, Ordered {

    @Autowired private UserCenterClient userCenterClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        if(headers.containsKey(Constants.HEADER_TOKEN)){
            List<String> tokens = headers.get(Constants.HEADER_TOKEN);
            Result result = userCenterClient.userInfo(tokens.get(0));
            log.info("用户中心返回：" + result.toString());
            if(!result.isSuccess()){
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                log.info("token校验失败，返回401");
                return response.setComplete();
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        // 值越大，优先级越低
        return -100;
    }
}
