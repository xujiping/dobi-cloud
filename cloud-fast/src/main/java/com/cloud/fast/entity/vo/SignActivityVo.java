package com.cloud.fast.entity.vo;

import cn.hutool.core.util.StrUtil;
import com.cloud.base.constants.Constants;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xujiping
 * @date 2019-08-17 14:31
 */
@Data
public class SignActivityVo implements Serializable {

    private static final long serialVersionUID = -5383811834299093178L;

    private String id;

    private String title;

    private String subject;

    private String covers;

    private String images;

    private Date startTime;

    private Date endTime;

    private String time;

    private String location;

    public String getCovers() {
        return StrUtil.isBlank(covers) ? Constants.DEFAULT_COVER : covers;
    }
}
