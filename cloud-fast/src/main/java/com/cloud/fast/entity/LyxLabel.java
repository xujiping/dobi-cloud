package com.cloud.fast.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 推荐标签表
 * </p>
 *
 * @author xujiping
 * @since 2019-08-28
 */
@Data
public class LyxLabel extends Model<LyxLabel> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 推荐名称
     */
    private String name;
    /**
     * 主题
     */
    private String subject;
    /**
     * 喜欢数
     */
    private Integer likeCount;
    /**
     * 不喜欢数
     */
    private Integer dislikeCount;
    /**
     * 标签ID列表，用逗号分隔
     */
    private String keywords;


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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(Integer dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
