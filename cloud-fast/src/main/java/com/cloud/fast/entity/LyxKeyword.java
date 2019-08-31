package com.cloud.fast.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 关键词表
 * </p>
 *
 * @author xujiping
 * @since 2019-08-28
 */
public class LyxKeyword extends Model<LyxKeyword> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    /**
     * 正在使用的数量
     */
    private Long useCount;
    /**
     * 总使用量
     */
    private Long useTotal;


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

    public Long getUseCount() {
        return useCount;
    }

    public void setUseCount(Long useCount) {
        this.useCount = useCount;
    }

    public Long getUseTotal() {
        return useTotal;
    }

    public void setUseTotal(Long useTotal) {
        this.useTotal = useTotal;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "LyxKeyword{" +
        "id=" + id +
        ", name=" + name +
        ", useCount=" + useCount +
        ", useTotal=" + useTotal +
        "}";
    }
}
