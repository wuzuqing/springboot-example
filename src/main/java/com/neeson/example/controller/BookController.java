package com.neeson.example.controller;


import com.neeson.example.book.SearchBookUtil;
import com.neeson.example.book.SqlHelper;
import com.neeson.example.service.impl.BookServiceImpl;
import com.neeson.example.util.response.ResponseResult;
import com.neeson.example.util.response.RestResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api("小说接口")
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @ApiOperation(value = "下载小说", produces = "application/json")
    @RequestMapping(value = "/downloadBook/{type}/{bookName}", method = RequestMethod.POST)
    public ResponseResult downloadBook(@PathVariable String type, @PathVariable String bookName) {
        SqlHelper.getInstance().initBookService(bookService);
        SearchBookUtil.searchByType(type, bookName);
        return RestResultGenerator.genResult("success", "获取成功");
    }
}
