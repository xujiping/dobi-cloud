package com.cloud.admin.fast.controller;

import cn.hutool.core.util.ReUtil;
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

    @Autowired private GjBookMenuService menuService;

    @ApiOperation(value = "html")
    @PassToken
    @GetMapping("html")
    public String html() {
        MenuContentDto menuContentDto = null;
        for (int i = 1; i < 82; i++) {
            String url = "https://www.daodejing.org/" + i + ".html";
            String html = HttpUtil.get(url);
            menuContentDto = new MenuContentDto();
            menuContentDto.setBookId(1L);
            menuContentDto.setWeight(i);
            Document doc = Jsoup.parse(html);
            Elements style4 = doc.select("p.STYLE4");
//            for (int i = 0; i < style4.size(); i++) {
//                System.out.println(i + ": " +style4.get(i));
//            }
            if (style4 == null || style4.size() <= 0){
                continue;
            }
            menuContentDto.setTitle(StrUtil.replace(style4.get(0).text(), "[原文]", "").trim());
            int sIndex = -1;
            int eIndex = style4.size();
            int ts = -1;
            int te = style4.size();
            String content = style4.get(1).text();
            String desc = content;
            if (content.length() > 50){
                desc = content.substring(0, 50) + "...";
            }
            menuContentDto.setDesc(ReUtil.replaceAll(desc, "[①②③④⑤⑥⑦⑧⑨⑩]", ""));
            menuContentDto.setContent(content);
            String yw = "";
            String zs = "";
            for (int j = 0; j < style4.size(); j++) {
                String text = style4.get(j).text();
                if (text.contains("[译文]")){
                    sIndex = j + 1;
                }
                if (text.contains("[注释]")){
                    eIndex = j;
                    ts = j + 1;
                }
                if (sIndex > 0 && j>= sIndex && j < eIndex){
                    yw += text;
                }
                if (text.contains("延伸阅读1")){
                    te = j;
                }
                if (ts > 0 && j >= ts && j < te){
                    zs += text;
                }
            }
            menuContentDto.setTransText(yw);
            menuContentDto.setAnnontation(zs);
//            menuContentDto.setAnnontation(style4.get(7).text() + style4.get(8).text());
            menuService.addMenuAndContent(menuContentDto);
        }
        return "success";
    }
}
