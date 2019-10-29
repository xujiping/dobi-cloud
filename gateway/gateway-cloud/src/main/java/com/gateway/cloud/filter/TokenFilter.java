package com.gateway.cloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
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

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        if(headers.containsKey("ucToken")){
            List<String> tokens = headers.get("ucToken");
            log.info("token=" + tokens.get(0));
        }
        // todo 在网关中心处理校验
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        // 值越大，优先级越低
        return -100;
    }
}
