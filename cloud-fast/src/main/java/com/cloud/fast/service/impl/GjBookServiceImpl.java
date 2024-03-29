package com.cloud.fast.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.base.constants.Constants;
import com.cloud.base.constants.ResultCode;
import com.cloud.base.exception.BusinessException;
import com.cloud.fast.entity.GjAuthor;
import com.cloud.fast.entity.GjBook;
import com.cloud.fast.entity.GjCategory;
import com.cloud.fast.entity.dto.BookDto;
import com.cloud.fast.entity.vo.GjBookSimpleVo;
import com.cloud.fast.entity.vo.GjBookVo;
import com.cloud.fast.mapper.GjBookMapper;
import com.cloud.fast.service.GjAuthorService;
import com.cloud.fast.service.GjBookService;
import com.cloud.fast.service.GjCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 古籍-书籍 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-11-08
 */
@Service
public class GjBookServiceImpl extends ServiceImpl<GjBookMapper, GjBook> implements GjBookService {

    @Autowired
    private GjCategoryService categoryService;

    @Autowired
    private GjAuthorService authorService;

    @Override
    public GjBookVo get(Long bookId) {
        GjBookVo bookVo = new GjBookVo();
        if (bookId == null) {
            return bookVo;
        }
        GjBook book = selectById(bookId);
        if (book == null || book.getStatus().equals(Constants.STATUS_UNENABLE)) {
            throw new BusinessException(ResultCode.BOOK_NOT_EXIST);
        }
        return wrapper(book);
    }

    @Override
    public GjBookVo wrapper(GjBook book) {
        GjBookVo gjBook = new GjBookVo();
        if (book == null) {
            return gjBook;
        }
        BeanUtils.copyProperties(book, gjBook);
        Integer categoryId = book.getCategoryId();
        String authorId = book.getAuthorId();
        if (categoryId != null) {
            gjBook.setGjCategory(categoryService.selectById(categoryId));
        }
        if (authorId != null) {
            gjBook.setGjAuthor(authorService.selectById(authorId));
        }
        return gjBook;
    }

    @Override
    public GjBookSimpleVo wrapperSimple(GjBook book) {
        GjBookSimpleVo gjBook = new GjBookSimpleVo();
        if (book == null) {
            return gjBook;
        }
        BeanUtils.copyProperties(book, gjBook);
        Integer categoryId = book.getCategoryId();
        String authorId = book.getAuthorId();
        if (categoryId != null) {
            gjBook.setGjCategory(categoryService.selectById(categoryId));
        }
        if (authorId != null) {
            gjBook.setGjAuthor(authorService.selectById(authorId));
        }
        return gjBook;
    }

    @Override
    public GjBookVo getOrAdd(BookDto bookDto) {
        return null;
    }

    @Override
    public Page<GjBookSimpleVo> page(Page<GjBook> page) {
        page = selectPage(page);
        Page<GjBookSimpleVo> simpleVoPage = new Page<>(page.getCurrent(), page.getSize());
        List<GjBook> records = page.getRecords();
        if (records != null && records.size() > 0) {
            List<GjBookSimpleVo> list = records.stream().map(this::wrapperSimple).collect(Collectors.toList());
            simpleVoPage.setRecords(list);
        }
        return simpleVoPage;
    }

    @Override
    public GjBook get(String name) {
        Wrapper<GjBook> wrapper = new EntityWrapper<>();
        wrapper.eq("book_name", name);
        return selectOne(wrapper);
    }

}
