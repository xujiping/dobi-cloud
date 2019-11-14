package com.cloud.fast.controller;

import com.cloud.auth.jwt.PassToken;
import com.cloud.fast.entity.dto.BookDto;
import com.cloud.fast.entity.vo.GjBookVo;
import com.cloud.fast.service.GjBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 古籍-书籍 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2019-11-08
 */
@RestController
@RequestMapping("/gjBook")
@Api(tags = "古籍-书籍")
public class GjBookController {

    @Autowired
    private GjBookService bookService;

    @PassToken
    @ApiOperation(value = "查询书籍信息", httpMethod = "GET")
    @GetMapping("info/{bookId}")
    public GjBookVo info(@PathVariable Long bookId) {
        return bookService.get(bookId);
    }

    @PassToken
    @ApiOperation(value = "添加新书籍", httpMethod = "POST")
    @PostMapping("new")
    public GjBookVo newBook(@RequestBody BookDto bookDto) {
        return bookService.newBook(bookDto);
    }

}

