package com.cloud.admin.fast.service;

import com.cloud.admin.fast.entity.dto.MenuContentDto;

import java.util.List;

/**
 * 爬虫服务
 * @Author: xujiping
 * @Date: 2019年12月30日 0030 下午 02:13:06
 * @Version 1.0
 */
public interface ReptileService {

    /**
     * 通用爬虫
     * @param url
     * @param menuId
     * @param menuClass
     * @param menuListselect
     * @param menuAttr
     * @param menuUrlPre
     * @param bookId
     * @param preview
     * @param titleSelect
     * @param titleIndex
     * @param contentSelect
     * @param contentIndex
     * @param ignores
     * @return
     */
    List<MenuContentDto> common(String url, String menuId, String menuClass, String menuListselect, String menuAttr,
                                String menuUrlPre, Long bookId, boolean preview, String titleSelect, int titleIndex,
                                String contentSelect, int contentIndex, String ignores);

    /**
     * 读取内容
     * @param url
     * @param menuContentDto
     * @param titleSelect
     * @param titleIndex
     * @param contentSelect
     * @param contentIndex
     * @param ignores
     * @return
     */
    boolean readContent(String url, MenuContentDto menuContentDto, String titleSelect, int titleIndex,
                        String contentSelect, int contentIndex, String ignores);
}
