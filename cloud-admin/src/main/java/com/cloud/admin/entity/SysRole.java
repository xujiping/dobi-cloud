package com.cloud.admin.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;
import lombok.AllArgsConstructor;

/**
 * <p>
 * 平台角色
 * </p>
 *
 * @author xujiping
 * @since 2019-06-06
 */
public class SysRole extends Model<SysRole> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 简介
     */
    private String roleIntro;
    /**
     * 平台ID
     */
    private Integer platformId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后更新时间
     */
    private Date updateTime;
    /**
     * 状态：0不可用 1正常
     */
    private Integer status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleIntro() {
        return roleIntro;
    }

    public void setRoleIntro(String roleIntro) {
        this.roleIntro = roleIntro;
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
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
        return "SysRole{" +
        "id=" + id +
        ", roleName=" + roleName +
        ", roleIntro=" + roleIntro +
        ", platformId=" + platformId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", status=" + status +
        "}";
    }
}
