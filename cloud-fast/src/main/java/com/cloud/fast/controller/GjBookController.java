package com.cloud.fast.controller;

import com.cloud.fast.entity.vo.GjBookVo;
import com.cloud.fast.service.GjBookService;
import io.swagger.annotations.Api;
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

    @GetMapping("info/{bookId}")
    public GjBookVo info(@PathVariable Long bookId) {
        return bookService.get(bookId);
    }

}

