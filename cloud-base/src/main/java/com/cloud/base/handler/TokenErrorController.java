package com.cloud.base.handler;

import com.cloud.base.constants.ResultCode;
import io.swagger.annotations.Api;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xujiping
 * @date 2019-10-30 14:03
 */
@RestController
@Api(value = "handle filter throws exception", description = "处理filter抛出的异常")
public class TokenErrorController extends BasicErrorController {

    public TokenErrorController() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    @Override
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        map.put("code",ResultCode.TOKEN_FAIL.code());
        map.put("msg",ResultCode.TOKEN_FAIL.msg());
        return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
    }
}
