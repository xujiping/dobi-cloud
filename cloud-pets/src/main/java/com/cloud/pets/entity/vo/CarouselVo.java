package com.cloud.pets.entity.vo;

/**
 * @author xujiping
 * @date 2019-01-25 14:04
 */
public class CarouselVo {

    /**
     * 图片完整链接
     */
    private String url;

    /**
     * 跳转
     */
    private String skip;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSkip() {
        return skip;
    }

    public void setSkip(String skip) {
        this.skip = skip;
    }

    @Override
    public String toString() {
        return "CarouselVo{" +
                "url='" + url + '\'' +
                ", skip='" + skip + '\'' +
                '}';
    }
}
