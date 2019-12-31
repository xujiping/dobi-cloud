package com.cloud.admin.fast.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.cloud.admin.fast.entity.dto.MenuContentDto;
import com.cloud.admin.fast.service.GjBookMenuService;
import com.cloud.admin.fast.service.ReptileService;
import com.cloud.auth.jwt.PassToken;
import com.cloud.base.constants.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xujiping
 * @date 2019-12-14 12:28
 */
@RestController
@RequestMapping("reptile")
@Api(tags = "爬虫")
@ResponseResult
public class ReptileController {

    @Autowired
    private GjBookMenuService menuService;

    @Autowired
    private ReptileService reptileService;

    @ApiOperation(value = "html")
    @PassToken
    @GetMapping("html")
    public String html1() {
        MenuContentDto menuContentDto;
        String url = "https://so.gushiwen.org/guwen/book_39.aspx";
        String html = HttpUtil.get(url);
        Document doc = Jsoup.parse(html);
        Elements bookcont = doc.getElementsByClass("bookcont");
        Elements aList = bookcont.select("span > a");
        for (int i = 0; i < aList.size(); i++) {
            Element element = aList.get(i);
            String href = element.attr("href");
            if (StrUtil.isBlank(href)) {
                continue;
            }
            href = "https://so.gushiwen.org" + href;
            System.out.println(href);
            menuContentDto = new MenuContentDto();
            menuContentDto.setBookId(10L);
            menuContentDto.setWeight(i + 1);
            if (menuService.readHtml(href, menuContentDto)) {
                System.out.println(menuContentDto);
                menuService.addMenuAndContent(menuContentDto);
            }
        }
        return "success";
    }

    @ApiOperation(value = "通用html爬取")
    @PassToken
    @GetMapping("common")
    public List<MenuContentDto> html(@ApiParam(required = true, name = "preview", value = "预览", defaultValue = "true")
                                     @RequestParam(defaultValue = "true") boolean preview,
                                     @ApiParam(required = true, name = "url", value = "爬取地址")
                                     @RequestParam String url,
                                     @ApiParam(name = "menuId", value = "菜单ID名")
                                     @RequestParam(required = false) String menuId,
                                     @ApiParam(name = "menuClass", value = "菜单类名")
                                     @RequestParam(required = false) String menuClass,
                                     @ApiParam(name = "menuListselect", value = "菜单列表Elements，遍历此处并得到每个菜单连接的标签对象")
                                     @RequestParam(required = false) String menuListselect,
                                     @ApiParam(name = "menuAttr", value = "菜单标签属性名称")
                                     @RequestParam(required = false) String menuAttr,
                                     @ApiParam(name = "menuUrlPre", value = "菜单链接前缀，如果获取到的是完整的则不需要")
                                     @RequestParam(required = false) String menuUrlPre,
                                     @ApiParam(required = true, name = "bookId", value = "书籍ID")
                                     @RequestParam Long bookId,
                                     @ApiParam(name = "titleSelect", value = "标题选择")
                                     @RequestParam(required = false) String titleSelect,
                                     @ApiParam(name = "titleIndex", value = "标题下标")
                                     @RequestParam(required = false, defaultValue = "0") Integer titleIndex,
                                     @ApiParam(name = "contentSelect", value = "内容选择")
                                     @RequestParam(required = false) String contentSelect,
                                     @ApiParam(name = "contentIndex", value = "内容下标")
                                     @RequestParam(required = false, defaultValue = "0") Integer contentIndex,
                                     @ApiParam(name = "ignores", value = "忽略列表")
                                     @RequestParam(required = false) String ignores) {
        return reptileService.common(url, menuId, menuClass, menuListselect, menuAttr, menuUrlPre, bookId, preview, titleSelect, titleIndex, contentSelect, contentIndex, ignores);
    }

}
