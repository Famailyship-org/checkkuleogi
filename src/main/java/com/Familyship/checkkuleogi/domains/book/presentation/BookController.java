package com.Familyship.checkkuleogi.domains.book.presentation;


import com.Familyship.checkkuleogi.domains.book.dto.BookMBTIRequest;
import com.Familyship.checkkuleogi.domains.book.dto.BookMBTIResponse;
import com.Familyship.checkkuleogi.domains.book.service.BookService;
import com.Familyship.checkkuleogi.global.domain.response.CommonResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    @Autowired
    private BookService bookService;


    @PostMapping("")
    public CommonResponseEntity<BookMBTIResponse> createBook(@RequestBody BookMBTIRequest request) {
        return CommonResponseEntity.success(bookService.createBook(request));
    }
}