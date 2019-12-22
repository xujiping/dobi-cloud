package com.cloud.admin.fast.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.admin.fast.entity.GjBookContent;
import com.cloud.admin.fast.entity.GjBookMenu;
import com.cloud.admin.fast.entity.dto.MenuContentDto;
import com.cloud.admin.fast.mapper.GjBookMenuMapper;
import com.cloud.admin.fast.service.GjBookContentService;
import com.cloud.admin.fast.service.GjBookMenuService;
import com.cloud.base.constants.Constants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 古籍-书籍目录表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-12-12
 */
@Service
public class GjBookMenuServiceImpl extends ServiceImpl<GjBookMenuMapper, GjBookMenu> implements GjBookMenuService {

    @Autowired
    GjBookContentService contentService;

    @Override
    public Page<GjBookMenu> page(Page<GjBookMenu> page) {
        if (page == null) {
            return null;
        }
        Wrapper<GjBookMenu> wrapper = new EntityWrapper<>();
        wrapper.eq("status", Constants.STATUS_NORMAL);
        wrapper.orderBy("weight");
        return selectPage(page, wrapper);
    }

    @Override
    public boolean addMenuAndContent(MenuContentDto menuContentDto) {
        System.out.println(JSONUtil.parse(menuContentDto).toStringPretty());
        List<GjBookMenu> list = getByBook(menuContentDto.getBookId(), menuContentDto.getTitle());
        if (list != null && list.size() > 0) {
            return false;
        }
        GjBookContent content;
        GjBookMenu menu = new GjBookMenu();
        BeanUtils.copyProperties(menuContentDto, menu);
        boolean insert = insert(menu);
        if (insert) {
            content = new GjBookContent();
            BeanUtils.copyProperties(menuContentDto, content);
            content.setAnnotation(menuContentDto.getAnnontation());
            content.setMenuId(menu.getId());
            contentService.insert(content);
        }
        return false;
    }

    @Override
    public List<GjBookMenu> getByBook(Long bookId, String title) {
        Wrapper<GjBookMenu> wrapper = new EntityWrapper<>();
        if (bookId != null) {
            wrapper.eq("book_id", bookId);
        }
        if (StrUtil.isNotBlank(title)) {
            wrapper.eq("title", title);
        }
        return selectList(wrapper);
    }

    @Override
    public boolean readHtml(String url, MenuContentDto menuContentDto) {
        if (StrUtil.isBlank(url)) {
            return false;
        }
        String html = HttpUtil.get(url);
        Document doc = Jsoup.parse(html);
        Element cont = doc.select(".sons > .cont").get(0);
        Elements span = cont.select("span");
        menuContentDto.setTitle(span.text());
        Element contson = cont.select(".contson").get(0);
        menuContentDto.setContent(contson.html());
        menuContentDto.setDesc(StrUtil.sub(contson.text(), 0, 50));
        // 译文
        Element yz = cont.select("a:contains(译注)").get(0);
        String href = yz.attr("href");
        String id = href.substring(21, href.length() - 1);
        url = "https://so.gushiwen.org/guwen/ajaxbfanyi.aspx?id=" + id;
        html = HttpUtil.get(url);
        doc = Jsoup.parse(html);
        Element yw = doc.select("p:contains(译文)").get(0);
        Element zs = doc.select("p:contains(注释)").get(0);
        String replace = StrUtil.replace(yw.html(), "<strong>译文<br /></strong>", "");
        String replace2 = StrUtil.replace(zs.html(), "<strong>注释</strong><br />", "");
        menuContentDto.setTransText(replace);
        menuContentDto.setAnnontation(replace2);
        if (StrUtil.isNotBlank(menuContentDto.getTitle()) && StrUtil.isNotBlank(menuContentDto.getContent())) {
            return true;
        }
        return false;
    }

    @Override
    public GjBookMenu getByWeight(Long bookId, Integer weight) {
        if (bookId == null || weight == null || weight < 0) {
            return null;
        }
        Wrapper<GjBookMenu> wrapper = new EntityWrapper<>();
        wrapper.eq("book_id", bookId);
        wrapper.eq("weight", weight);
        return selectOne(wrapper);
    }
}
