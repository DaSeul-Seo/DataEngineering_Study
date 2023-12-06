package com.example.basic.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.basic.model.BookDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/api")
public class BasicController {

    @GetMapping("/hello")
    public String getHello(Model model) {

        return "hello";
    }

    @GetMapping("/mustache")
    public String mustache(Model model) {
        // String  데이터 전달
        model.addAttribute("name", "lee");

        // int 데이터 전달
        model.addAttribute("age", 20);

        // html tag 전달
        model.addAttribute("company", "<span>Google</span>");

        // bool 데이터 전달
        model.addAttribute("person", false);

        return "mustache";
    }

    @GetMapping("/book")
    public String book(Model model) {
        log.info("[BasicController][book] Start");
        BookDto bookDto = new BookDto();

        bookDto.setBookId(1L);
        bookDto.setIsBn(false);
        bookDto.setBookTitle("<span>Book Title</span>");
        bookDto.setAuthor("Seo");
        bookDto.setPrice(5000D);

        log.info("bookDto : " + bookDto.toString());

        model.addAttribute("book", bookDto);
        return "book";
    }

    @GetMapping("/booklist")
    public String bookList(Model model) {
        List<BookDto> bookList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Boolean isbn = false;
            if (i > 3) {
                isbn = true;
            }
            // 생성자를 통해 Date input
            BookDto bookDto = new BookDto(
                (long)i,
                isbn,
                "Book Title_" + i,
                "Author_" + i, 
                (double)(i * 5000)
            );
            
            bookList.add(bookDto);
        }

        model.addAttribute("bookList", bookList);

        return "bookList";
    }
}
