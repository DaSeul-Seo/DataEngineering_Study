package com.example.advanced.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.advanced.model.dto.TodoDto;
import com.example.advanced.service.TodoService;

import lombok.extern.slf4j.Slf4j;

// Client의 요청의 진입점은 Controller
@Slf4j  // 로그
@Controller // 결과를 화면 단에서 확인한다
@RequestMapping("/todo") // 기본 url 설정
public class TodoController {
    @Autowired  // DI(의존성 주임) : 자동으로 인스턴스를 생성
    private TodoService todoService;    // Controller에서 받은 요청은 Service에 넘겨준다.

    // 전체 TodoList
    @GetMapping("/select")  // Get으로 요청을 받는다.
    public String selectAll(Model model) {  // View에 넘겨주기 위한 Model 설정
        log.info("[TodoController][selectAll] Start");
        // Service에서 전체 리스트를 출력하는 기능을 쓴다.
        List<TodoDto> todoList = todoService.selectAll();
        log.info("todoList: " + todoList.size());   // 전체 리스트 수 출력

        // View에서 쓸 이름 설정
        model.addAttribute("todoList", todoList);
        return "todo";
    }

    // 데이터 추가
    @PostMapping("/insert") // post로 요청을 받는다.
    public String insertTodo(@ModelAttribute TodoDto todoDto) { // Dto에 정의된 모델을 받는다.
        log.info("[TodoController][insertTodo] Start");
        log.info("Insert Data : " + todoDto.toString());
        // Service에서 데이터를 추가하는 기능을 호출
        todoService.insertTodo(todoDto);
        log.info("Insert Success");

        // 화면단을 다시 로딩
        return "redirect:/todo/select";
    }

    @GetMapping("/update/{id}")    // post로 요청을 받는다.
    public String updateTodo(@PathVariable long id) {   // 업데이트 할 id를 받는다.
        log.info("[TodoController][updateTodo] Start");
        log.info("update id : " + id);

        todoService.updateTodo(id); // Service에서 데이터를 업데이트하는 기능을 호출
        log.info("Update Success");

        // 화면단을 다시 로딩
        return "redirect:/todo/select";
    }

    @GetMapping("/delete/{id}")    // post로 요청을 받는다.
    public String deleteTodo(@PathVariable long id) {   // 삭제할 id를 받는다.
        log.info("[TodoController][deleteTodo] Start");
        log.info("delete id : " + id);

        todoService.deleteTodo(id); // Service에서 데이터를 삭제하는 기능을 호출
        log.info("Delete Success");

        // 화면단을 다시 로딩
        return "redirect:/todo/select";
    }
}
