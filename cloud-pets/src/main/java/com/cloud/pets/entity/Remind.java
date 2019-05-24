package com.cloud.pets.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xujiping
 * @since 2018-12-30
 */
public class Remind extends Model<Remind> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 宠物ID
     */
    private Integer petsId;
    /**
     * 提醒类型ID
     */
    private Integer typeId;
    /**
     * 提醒名称
     */
    private String name;
    /**
     * 提醒时间
     */
    private Date remindTime;
    /**
     * 重复方式    1：仅一次    2：每天    3：法定工作日  4：法定休息日
     */
    private Integer recurrence;
    /**
     * 提醒方式      1：系统通知     2：短信提醒       3：都通知
     */
    private Integer reminders;
    /**
     * 创建时间
     */
    private Date createTime;
    private String status;
    /**
     * 更新时间
     */
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPetsId() {
        return petsId;
    }

    public void setPetsId(Integer petsId) {
        this.petsId = petsId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(Date remindTime) {
        this.remindTime = remindTime;
    }

    public Integer getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(Integer recurrence) {
        this.recurrence = recurrence;
    }

    public Integer getReminders() {
        return reminders;
    }

    public void setReminders(Integer reminders) {
        this.reminders = reminders;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return "Remind{" +
        "id=" + id +
        ", petsId=" + petsId +
        ", typeId=" + typeId +
        ", name=" + name +
        ", remindTime=" + remindTime +
        ", recurrence=" + recurrence +
        ", reminders=" + reminders +
        ", createTime=" + createTime +
        ", status=" + status +
        ", updateTime=" + updateTime +
        "}";
    }
}
