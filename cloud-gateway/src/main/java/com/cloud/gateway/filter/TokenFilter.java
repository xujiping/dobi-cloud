package com.cloud.gateway.filter;

import com.cloud.gateway.config.JwtConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

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

    @Autowired
    private JwtConfig jwtConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("拦截路径：" + jwtConfig.getExcludePathPatterns());
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if (token == null || token.isEmpty()) {
            log.info("token is empty...");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        // 值越大，优先级越低
        return -100;
    }
}
