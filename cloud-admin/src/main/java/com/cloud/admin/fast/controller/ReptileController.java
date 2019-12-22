package com.cloud.admin.fast.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.cloud.admin.fast.entity.dto.MenuContentDto;
import com.cloud.admin.fast.service.GjBookMenuService;
import com.cloud.auth.jwt.PassToken;
import com.cloud.base.constants.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(value = "html")
    @PassToken
    @GetMapping("html")
    public String html() {
        MenuContentDto menuContentDto;
        String url = "https://so.gushiwen.org/guwen/book_63.aspx";
        String html = HttpUtil.get(url);
        Document doc = Jsoup.parse(html);
        Elements bookcont = doc.getElementsByClass("bookcont");
        Elements aList = bookcont.select("span > a");
        for (int i = 0; i < aList.size(); i++) {
            Element element = aList.get(i);
            String href = element.attr("href");
            if (StrUtil.isBlank(href)){
                continue;
            }
            href = "https://so.gushiwen.org" + href;
            System.out.println(href);
            menuContentDto = new MenuContentDto();
            menuContentDto.setBookId(6L);
            menuContentDto.setWeight(i + 1);
            if (menuService.readHtml(href, menuContentDto)) {
                System.out.println(menuContentDto);
                menuService.addMenuAndContent(menuContentDto);
            }
        }
        return "success";
    }
}
