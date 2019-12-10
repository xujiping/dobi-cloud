package com.cloud.fast.controller;


import com.cloud.base.constants.ResponseResult;
import com.cloud.fast.entity.GjLink;
import com.cloud.fast.service.GjLinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 古籍-链接 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2019-11-08
 */
@RestController
@RequestMapping("/gjLink")
@ResponseResult
@Api(tags = "古籍-链接")
public class GjLinkController {

    @Autowired private GjLinkService linkService;

    @ApiOperation(value = "下载链接列表")
    @GetMapping("{bookId}")
    public List<GjLink> list(@ApiParam(required = true, name = "bookId", value = "书籍ID")
            @PathVariable(value = "bookId") Long bookId){
        return linkService.listByBookId(bookId);
    }

}

