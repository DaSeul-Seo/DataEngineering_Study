package com.example.advanced.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.advanced.model.dto.TodoDto;
import com.example.advanced.service.TodoService;

import lombok.extern.slf4j.Slf4j;

// @Controller
@Slf4j
@Controller
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/")
    public String selectAll(Model model) {
        log.info("[TodoController][selectAll] Start");

        model.addAttribute("todoList", todoService.selectAll());

        return "todo";
    }

    @PostMapping("/save")
    public String insertData(@ModelAttribute TodoDto dto) {
        log.info("[TodoController][insertData] Start");

        todoService.insertData(dto);
        return "redirect:/todo/";
    }

    @GetMapping("/finished/{id}")
    public String updateData(@PathVariable long id) {
        log.info("[TodoController][updateData] Start");
        log.info("id: " + id);

        todoService.updateData(id);
        return "redirect:/todo/";
    }

    @GetMapping("/delete/{id}")
    public String deleteData(@PathVariable long id) {
        log.info("[TodoController][deleteData] Start");
        log.info("id: " + id);

        todoService.deleteData(id);
        return "redirect:/todo/";
    }
}
