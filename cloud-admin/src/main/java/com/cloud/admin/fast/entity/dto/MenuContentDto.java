package com.cloud.admin.fast.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xujiping
 * @date 2019-12-14 13:44
 */
@Data
public class MenuContentDto implements Serializable {

    private static final long serialVersionUID = 3585206794238264301L;

    private Long bookId;

    private String title;

    private String desc;

    private Integer weight;

    private String content;

    private String transText;

    private String annontation;
}
