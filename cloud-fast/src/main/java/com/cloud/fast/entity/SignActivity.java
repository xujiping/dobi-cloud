package com.cloud.fast.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import lombok.Data;

/**
 * <p>
 * 活动报名表
 * </p>
 *
 * @author xujiping
 * @since 2019-08-02
 */
@Data
public class SignActivity extends Model<SignActivity> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private String id;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 标题
     */
    private String title;
    /**
     * 主题
     */
    private String subject;
    /**
     * 详情，富文本
     */
    private String desc;
    /**
     * 图片json
     */
    private String images;
    /**
     * 人数限制，负数代表无限制
     */
    private Integer maxPeople;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 报名开始时间
     */
    private Date signStartTime;
    /**
     * 报名结束时间
     */
    private Date signEndTime;
    /**
     * 地点
     */
    private String location;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 详细地址
     */
    private String locationDetail;
    /**
     * 联系人姓名
     */
    private String contactName;
    /**
     * 联系电话，多个用半角逗号分隔
     */
    private String contactPhone;

    private String wxNumber;
    /**
     * 提交表单ID
     */
    private Integer formId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建者用户ID
     */
    private String createBy;
    /**
     * 更新时间
     */
    private Date updateTime;

    private Byte status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Integer getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SignActivity{" +
        "id=" + id +
        ", userId=" + userId +
        ", title=" + title +
        ", subject=" + subject +
        ", desc=" + desc +
        ", images=" + images +
        ", maxPeople=" + maxPeople +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", location=" + location +
        ", latitude=" + latitude +
        ", longitude=" + longitude +
        ", contactName=" + contactName +
        ", contactPhone=" + contactPhone +
        ", formId=" + formId +
        ", createTime=" + createTime +
        ", createBy=" + createBy +
        ", updateTime=" + updateTime +
        "}";
    }
}
