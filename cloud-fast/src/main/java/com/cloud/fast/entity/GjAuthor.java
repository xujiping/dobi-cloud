package com.cloud.fast.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 古籍-作者
 * </p>
 *
 * @author xujiping
 * @since 2019-11-08
 */
public class GjAuthor extends Model<GjAuthor> {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 真实姓名
     */
    private String name;
    /**
     * 字
     */
    private String zi;
    /**
     * 号
     */
    private String hao;
    /**
     * 笔名
     */
    private String biName;
    /**
     * 简介
     */
    private String introduce;
    /**
     * 出生日期
     */
    private Date birthday;
    /**
     * 死亡日期
     */
    private Date deathday;
    /**
     * 详细介绍
     */
    private String detail;
    private Date createTime;
    private Date updateTime;
    private Integer status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZi() {
        return zi;
    }

    public void setZi(String zi) {
        this.zi = zi;
    }

    public String getHao() {
        return hao;
    }

    public void setHao(String hao) {
        this.hao = hao;
    }

    public String getBiName() {
        return biName;
    }

    public void setBiName(String biName) {
        this.biName = biName;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getDeathday() {
        return deathday;
    }

    public void setDeathday(Date deathday) {
        this.deathday = deathday;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "GjAuthor{" +
        "id=" + id +
        ", name=" + name +
        ", zi=" + zi +
        ", hao=" + hao +
        ", biName=" + biName +
        ", introduce=" + introduce +
        ", birthday=" + birthday +
        ", deathday=" + deathday +
        ", detail=" + detail +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", status=" + status +
        "}";
    }
}
