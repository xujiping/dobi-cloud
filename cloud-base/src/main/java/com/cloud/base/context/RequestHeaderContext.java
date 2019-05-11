package com.cloud.base.context;

/**
 * 请求头参数包装
 * @author xujiping
 * @date 2018/5/17 11:40
 */
public class RequestHeaderContext {

    private static final ThreadLocal<RequestHeaderContext> REQUEST_HEADER_CONTEXT_THREAD_LOCAL = new ThreadLocal<>();
    private String userId;
    private String sign;
    private String timestamp;
    private String version;

    public String getTimestamp() {
        return timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public String getSign() {
        return sign;
    }

    public String getVersion() {
        return version;
    }

    public static RequestHeaderContext getInstance() {
        return REQUEST_HEADER_CONTEXT_THREAD_LOCAL.get();
    }

    public void setContext(RequestHeaderContext context) {
        REQUEST_HEADER_CONTEXT_THREAD_LOCAL.set(context);
    }

    public static void clean() {
        REQUEST_HEADER_CONTEXT_THREAD_LOCAL.remove();
    }

    private RequestHeaderContext(RequestHeaderContextBuild requestHeaderContextBuild) {
        this.userId = requestHeaderContextBuild.userId;
        this.sign = requestHeaderContextBuild.sign;
        this.timestamp = requestHeaderContextBuild.timestamp;
        this.version = requestHeaderContextBuild.version;
        setContext(this);
    }

    public static class RequestHeaderContextBuild {
        private String userId;
        private String sign;
        private String timestamp;
        private String version;

        public RequestHeaderContextBuild userId(String userId) {
            this.userId = userId;
            return this;
        }

        public RequestHeaderContextBuild sign(String sign) {
            this.sign = sign;
            return this;
        }

        public RequestHeaderContextBuild timestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public RequestHeaderContextBuild version(String version) {
            this.version = version;
            return this;
        }

        public RequestHeaderContext bulid() {
            return new RequestHeaderContext(this);
        }
    }
}
