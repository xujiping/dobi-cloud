package com.cloud.admin.fast.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.cloud.admin.fast.entity.GjBookMenu;
import com.cloud.admin.fast.entity.dto.MenuContentDto;
import com.cloud.admin.fast.service.GjBookMenuService;
import com.cloud.admin.fast.service.ReptileService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xujiping
 * @Date: 2019年12月30日 0030 下午 02:15:32
 * @Version 1.0
 */
@Service
public class ReptileServiceImpl implements ReptileService {

    @Autowired
    private GjBookMenuService bookMenuService;

    @Override
    public List<MenuContentDto> common(String url, String menuId, String menuClass, String menuListselect,
                                       String menuAttr, String menuUrlPre, Long bookId, boolean preview,
                                       String titleSelect, int titleIndex, String contentSelect, int contentIndex,
                                       String ignores) {
        List<MenuContentDto> results = new ArrayList<>();
        MenuContentDto menuContentDto;
        String html = HttpUtil.get(url);
        Document doc = Jsoup.parse(html);
        // 获取菜单列表父标签
        Elements menuParents;
        Element menuParent;
        Elements menuList = null;
        if (StrUtil.isNotBlank(menuId)) {
            menuParent = doc.getElementById(menuId);
            menuList = menuParent.select(menuListselect);
        }
        if (StrUtil.isNotBlank(menuClass)) {
            menuParents = doc.getElementsByClass(menuClass);
            menuList = menuParents.select(menuListselect);
        }
        if (menuList == null && menuList.size() <= 0) {
            return results;
        }
        int startWeight = 1;
        List<GjBookMenu> menus = bookMenuService.getByBook(bookId, null);
        if (menus != null && menus.size() > 0) {
            startWeight = menus.get(menus.size() - 1).getWeight() + 1;
        }
        for (Element element : menuList) {
            String href = element.attr(menuAttr);
            if (StrUtil.isBlank(href)) {
                continue;
            }
            if (StrUtil.isNotBlank(menuUrlPre)) {
                href = menuUrlPre + href;
            }
            menuContentDto = new MenuContentDto();
            menuContentDto.setWeight(startWeight++);
            menuContentDto.setBookId(bookId);
            menuContentDto.setMenuUrl(href);
            if (readContent(href, menuContentDto, titleSelect, titleIndex, contentSelect, contentIndex, ignores)) {
                if (!preview) {
                    bookMenuService.addMenuAndContent(menuContentDto);
                }
            }
            results.add(menuContentDto);
        }
        return results;
    }

    @Override
    public boolean readContent(String url, MenuContentDto menuContentDto, String titleSelect, int titleIndex,
                               String contentSelect, int contentIndex, String ignores) {
        if (StrUtil.isBlank(url) || StrUtil.isBlank(titleSelect) || StrUtil.isBlank(contentSelect)) {
            return false;
        }
        List<String> ignoreList = new ArrayList<>();
        if (StrUtil.isNotBlank(ignores)) {
            ignoreList = StrUtil.split(ignores, ',');
        }
        String html = HttpUtil.get(url);
        Document doc = Jsoup.parse(html);
        // 标题
        Elements titleElements = doc.select(titleSelect);
        menuContentDto.setTitle(titleElements.get(titleIndex).text());
        // 正文
        Element contentElement = doc.select(contentSelect).get(contentIndex);
        if (ignoreList != null && ignoreList.size() > 0) {
            for (String ignore : ignoreList) {
                contentElement.select(ignore).remove();
            }
        }
        menuContentDto.setContent(contentElement.html());
        menuContentDto.setDesc(StrUtil.sub(contentElement.text(), 0, 50));
        if (StrUtil.isNotBlank(menuContentDto.getTitle()) && StrUtil.isNotBlank(menuContentDto.getContent())) {
            return true;
        }
        return false;
    }
}
