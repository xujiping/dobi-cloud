package com.cloud.pets.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 圈子
 * </p>
 *
 * @author xujiping
 * @since 2018-12-29
 */
@Data
public class Circle extends Model<Circle> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 发布人ID
     */
    private String userId;
    /**
     * 主题：视频、图文
     */
    private String subject;
    /**
     * 类别ID
     */
    private Integer typeId;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 封面JSON
     */
    private String cover;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 状态
     */
    private String status;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
