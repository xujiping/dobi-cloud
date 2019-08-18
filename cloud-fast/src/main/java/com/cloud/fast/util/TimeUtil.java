package com.cloud.fast.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @author xujiping
 * @date 2019-08-17 16:30
 */
public class TimeUtil {

    /**
     * 格式化
     * @param startTime
     * @param endTime
     * @return
     */
    public static String format(Date startTime, Date endTime){
        String time = "";
        if (startTime != null){
            time = DateUtil.format(startTime, DatePattern.NORM_DATETIME_MINUTE_FORMAT);
        }
        if (endTime != null){
            time += " 至 " +  DateUtil.format(endTime, DatePattern.NORM_DATETIME_MINUTE_FORMAT);
        }
        return time;
    }
}
