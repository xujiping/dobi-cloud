package com.cloud.admin.fast.entity.vo;

import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.admin.fast.entity.GjBookMenu;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xujiping
 * @date 2019-12-13 15:46
 */
@Data
public class BookMenusVo implements Serializable {

    private static final long serialVersionUID = -602348867768508998L;

    private String bookName;

    private String author;

    private Page<GjBookMenu> page;
}
