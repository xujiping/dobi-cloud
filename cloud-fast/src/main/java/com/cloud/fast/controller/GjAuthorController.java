package com.cloud.fast.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.auth.jwt.PassToken;
import com.cloud.base.constants.ResponseResult;
import com.cloud.fast.entity.GjAuthor;
import com.cloud.fast.entity.dto.AuthorDto;
import com.cloud.fast.service.GjAuthorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 古籍-作者 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2019-11-08
 */
@RestController
@RequestMapping("/gjAuthor")
@Api(tags = "古籍-作者")
@ResponseResult
public class GjAuthorController {

    @Autowired
    private GjAuthorService authorService;

    @PassToken
    @ApiOperation(value = "获取作者信息", httpMethod = "GET")
    @GetMapping("info/{authorId}")
    public GjAuthor info(@PathVariable Long authorId) {
        return authorService.get(authorId);
    }

    @PassToken
    @ApiOperation(value = "添加作者", httpMethod = "POST")
    @PostMapping("new")
    public GjAuthor newBook(@RequestBody AuthorDto authorDto) {
        return authorService.newAuthor(authorDto);
    }

    @ApiOperation(value = "分页查询")
    @PassToken
    @GetMapping("page")
    public Page<GjAuthor> page(@ApiParam(name = "page", value = "页码", defaultValue = "1")
                               @RequestParam(required = false, defaultValue = "1") Integer page,
                               @ApiParam(name = "size", value = "大小", defaultValue = "10")
                               @RequestParam(required = false, defaultValue = "10") Integer size) {
        Page<GjAuthor> pageObject = new Page<>(page, size);
        return authorService.page(pageObject);
    }

}

