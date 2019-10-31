package com.cloud.fast.client;

import com.cloud.base.constants.Result;
import com.cloud.base.constants.ResultCode;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author xujiping
 * @date 2019-10-31 10:15
 */
@Component
@Slf4j
public class UserCenterFactory implements FallbackFactory<UserCenterClient> {
    @Override
    public UserCenterClient create(Throwable throwable) {
        return new UserCenterClient() {
            @Override
            public Result userInfo(String ucToken) {
                log.error("查询用户信息失败");
                return Result.failure(ResultCode.FAIL);
            }
        };
    }
}
