package com.cloud.base.constants;

/**
 * 平台
 * @Author: xujiping
 * @Date: 2019年7月3日 0003 上午 11:00:39
 * @Version 1.0
 */
public enum PlatformEnum {

    USER_CENTER(10000, "用户中心");

    private int pId;
    private String pName;

    PlatformEnum() {
    }

    PlatformEnum(int pId, String pName) {
        this.pId = pId;
        this.pName = pName;
    }

    public int pId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String pName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public static String getpName(Integer pId){
        if (pId == null){
            return null;
        }
        PlatformEnum[] values = values();
        for (PlatformEnum p : values) {
            if (p.pId == pId){
                return p.pName();
            }
        }
        return null;
    }

}
