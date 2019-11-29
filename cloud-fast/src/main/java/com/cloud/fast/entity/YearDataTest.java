package com.cloud.fast.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author xujiping
 * @since 2019-11-25
 */
@Data
public class YearDataTest extends Model<YearDataTest> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String birthdays;
    private String sex;
    private String phone;
    private String remark;
    private Integer userId;
    private String idCard;
    private String idName;
    private Boolean accurate;
    private String realName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdays() {
        return birthdays;
    }

    public void setBirthdays(String birthdays) {
        this.birthdays = birthdays;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public Boolean getAccurate() {
        return accurate;
    }

    public void setAccurate(Boolean accurate) {
        this.accurate = accurate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "YearDataTest{" +
        "id=" + id +
        ", name=" + name +
        ", birthdays=" + birthdays +
        ", sex=" + sex +
        ", phone=" + phone +
        ", remark=" + remark +
        ", userId=" + userId +
        ", idCard=" + idCard +
        ", idName=" + idName +
        ", accurate=" + accurate +
        "}";
    }
}
