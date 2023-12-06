package com.example.advanced.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.advanced.model.dto.TodoDto;
import com.example.advanced.service.TodoService;

import lombok.extern.slf4j.Slf4j;

// Client의 요청의 진입점은 Controller
@Slf4j  // 로그를 찍는 라이브러리
@RestController // 결과를 Restapi로 받는다.
@RequestMapping("/api") // 기본 url 설정
public class TodoApiController {

    @Autowired  // DI(의존성 주임) : 자동으로 인스턴스를 생성
    private TodoService todoService;    // Controller에서 받은 요청은 데이터 처리를 위해 Service에 넘겨준다.

    // 전체 TodoList
    @GetMapping("/select")  // Get으로 요청을 받는다.
    public List<TodoDto> selectAll() {
        log.info("[TodoApiController][selectAll] Start");

        // Service에서 전체 리스트를 출력하는 기능을 호출
        List<TodoDto> todoList = todoService.selectAll();
        // 전체 리스트 수 출력
        log.info("todoList: " + todoList.size());
        
        // Service에서 받은 전체 리스트를 반환
        return todoList;
    }

    // 데이터 추가
    @PostMapping("/insert") // post로 요청을 받는다.
    public void insertTodo(@RequestBody TodoDto todoDto) {  // Dto에 정의된 모델을 받는다.
        log.info("[TodoApiController][insertTodo] Start");
        log.info("Insert Data : " + todoDto.toString());
        todoService.insertTodo(todoDto);    // Service에서 데이터를 추가하는 기능을 호출
        log.info("Insert Success");
    }

    // 데이터 업데이트
    @PostMapping("/update/{id}")    // post로 요청을 받는다.
    public void updateTodo(@PathVariable long id) { // 업데이트 할 id를 받는다.
        log.info("[TodoApiController][updateTodo] Start");
        log.info("update id : " + id);

        todoService.updateTodo(id); // Service에서 데이터를 업데이트하는 기능을 호출
        log.info("Update Success");
    }

    // 데이터 삭제
    @PostMapping("/delete/{id}")    // post로 요청을 받는다.
    public void deleteTodo(@PathVariable long id) { // 삭제할 id를 받는다.
        log.info("[TodoApiController][deleteTodo] Start");
        log.info("delete id : " + id);

        todoService.deleteTodo(id); // Service에서 데이터를 삭제하는 기능을 호출
        log.info("Delete Success");
    }
}
