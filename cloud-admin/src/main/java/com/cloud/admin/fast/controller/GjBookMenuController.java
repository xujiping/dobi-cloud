package com.cloud.admin.fast.controller;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.admin.fast.entity.GjBookMenu;
import com.cloud.admin.fast.entity.vo.BookMenusVo;
import com.cloud.admin.fast.entity.vo.GjBookVo;
import com.cloud.admin.fast.service.GjBookMenuService;
import com.cloud.admin.fast.service.GjBookService;
import com.cloud.auth.jwt.PassToken;
import com.cloud.base.constants.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 古籍-书籍目录表 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2019-12-12
 */
@RestController
@ResponseResult
@RequestMapping("/gjBookMenu")
@Api(tags = "古籍-书籍目录")
public class GjBookMenuController {

    @Autowired
    private GjBookMenuService bookMenuService;

    @Autowired
    private GjBookService bookService;

    @ApiOperation(value = "分页查询")
    @PassToken
    @GetMapping("page/{bookId}")
    public BookMenusVo page(@ApiParam(required = true, name = "bookId", value = "书籍ID")
                                 @PathVariable(name = "bookId") Long bookId,
                            @ApiParam(name = "page", value = "页码", defaultValue = "1")
                                 @RequestParam(required = false, defaultValue = "1") Integer page,
                            @ApiParam(name = "size", value = "大小", defaultValue = "10")
                                 @RequestParam(required = false, defaultValue = "10") Integer size) {
        BookMenusVo bookMenusVo = new BookMenusVo();
        GjBookVo bookVo = bookService.get(bookId);
        if (bookVo != null){
            bookMenusVo.setBookName(bookVo.getBookName());
            bookMenusVo.setAuthor(bookVo.getGjAuthor().getName());
        }
        Page<GjBookMenu> pageObject = new Page<>(page, size);
        Map<String, Object> condition = MapUtil.newHashMap(1);
        condition.put("book_id", bookId);
        pageObject.setCondition(condition);
        bookMenusVo.setPage(bookMenuService.page(pageObject));
        return bookMenusVo;
    }

}

