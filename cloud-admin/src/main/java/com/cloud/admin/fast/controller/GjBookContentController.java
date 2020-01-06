package com.cloud.admin.fast.controller;

import com.cloud.admin.fast.entity.vo.BookContentVo;
import com.cloud.admin.fast.service.GjBookContentService;
import com.cloud.auth.jwt.PassToken;
import com.cloud.base.constants.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 古籍-书籍内容 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2019-12-12
 */
@RestController
@RequestMapping("/gjBookContent")
@Api(tags = "古籍-书籍内容")
@ResponseResult
public class GjBookContentController {

    @Autowired
    private GjBookContentService bookContentService;

    @ApiOperation(value = "查看内容")
    @GetMapping("{menuId}")
    @PassToken
    public BookContentVo getContent(@ApiParam(required = true, name = "menuId", value = "目录ID")
                                    @PathVariable(name = "menuId") Long menuId) {
        return bookContentService.getByMenuId(menuId);
    }

    @ApiOperation(value = "更新")
    @PostMapping("{menuId}")
    @PassToken
    public BookContentVo updateContent(@ApiParam(required = true, name = "menuId", value = "目录ID")
                                       @PathVariable(name = "menuId") Long menuId,
                                       @ApiParam(name = "content", value = "正文内容")
                                       @RequestParam(required = false) String content,
                                       @ApiParam(name = "transText", value = "译文")
                                       @RequestParam(required = false) String transText,
                                       @ApiParam(name = "annotation", value = "注释")
                                       @RequestParam(required = false) String annotation) {
        return bookContentService.update(menuId, content, transText, annotation);
    }

}

