package com.cloud.admin.fast.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.admin.fast.entity.GjBook;
import com.cloud.admin.fast.entity.dto.BookDto;
import com.cloud.admin.fast.entity.vo.GjBookSimpleVo;
import com.cloud.admin.fast.entity.vo.GjBookVo;
import com.cloud.admin.fast.service.GjBookService;
import com.cloud.auth.jwt.PassToken;
import com.cloud.base.constants.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 古籍-书籍 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2019-12-12
 */
@RestController
@RequestMapping("/gjBook")
@Api(tags = "古籍-书籍")
@ResponseResult
public class GjBookController {

    @Autowired
    private GjBookService bookService;

    @ApiOperation(value = "分页查询")
    @PassToken
    @GetMapping("page")
    public Page<GjBookSimpleVo> page(@ApiParam(name = "page", value = "页码", defaultValue = "1")
                                     @RequestParam(required = false, defaultValue = "1") Integer page,
                                     @ApiParam(name = "size", value = "大小", defaultValue = "10")
                                     @RequestParam(required = false, defaultValue = "100") Integer size){
        Page<GjBook> pageObject = new Page<>(page, size);
        return bookService.page(pageObject);
    }

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
        return bookService.getOrAdd(bookDto);
    }

}

