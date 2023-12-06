package com.example.advanced.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.advanced.database.dao.TodoDao;
import com.example.advanced.model.dto.TodoDto;
import com.example.advanced.model.entity.TodoEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j  // 로그를 찍는 라이브러리
@Service    // 해당 클래스가 서비스 계층에 속한다는 어노테이션
public class TodoService {
    @Autowired  // DI(의존성 주임) : 자동으로 인스턴스를 생성
    private TodoDao todoDao;    // DB연결을 위한 DAO와 연결

    // 전체 리스트 출력 함수
    public List<TodoDto> selectAll() {
        log.info("[TodoService][selectAll] Start");
        // DAO에서 전체 리스트를 받아온다.
        List<TodoEntity> todoEntityList = todoDao.selectAll();

        // 전체 리스트를 Client에 전달해 주기 위해서는 Entity -> DTO를 변환해주어야 한다.
        // Entity는 DB(Data)와 직접적인 일을 처리하는 역할
        // DTO는 Client와의 일을 처리하는 역할
        List<TodoDto> todoDtoList = new ArrayList<>();
        // 결과 리스트를 DTO에 정의
        for (TodoEntity todo: todoEntityList) {
            TodoDto todoDto = new TodoDto();
            todoDto.setId(todo.getId());
            todoDto.setTitle(todo.getTitle());
            todoDto.setStatus(todo.getStatus());
            
            // 반환 리스트에 하나씩 담아준다.
            todoDtoList.add(todoDto);
        }
        // 전체 리스트를 반환
        return todoDtoList;
    }

    // 데이터 추가
    public void insertTodo(TodoDto todoDto) {
        log.info("[TodoService][insertTodo] Start");

        TodoEntity todoEntity = new TodoEntity();   // DAO에게 넘겨줄 객체 생성(Entity)
        todoEntity.setTitle(todoDto.getTitle());    // Entity에 값을 넣어준다.
        todoEntity.setStatus(false);  // 데이터 추가 시, 초기값은 무조건 false

        // DAO에 있는 데이터 추가해주는 기능 호출
        todoDao.insertTodo(todoEntity);
    }

    // 데이터 업데이트
    public void updateTodo(long id) {
        log.info("[TodoService][updateTodo] Start");

        // id가 0이하는 없기 때문에 그 이하의 값을 받으면 Error를 던져준다.
        if (id <= 0) {
            log.error("Error: [TodoService][updateTodo]");
            return;
        }

        // DAO에게 넘겨줄 객체 생성(Entity)
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setId(id);   // Entity에 값을 넣어준다.
        todoEntity.setStatus(true);   // 데이터 업데이트 시, 상태값을 True로 변경

        // DAO에 있는 데이터 업데이트해주는 기능 호출
        todoDao.updateTodo(todoEntity);
        
    }

    // 데이터 삭제
    public void deleteTodo(long id) {
        log.info("[TodoService][deleteTodo] Start");

        // id가 0이하는 없기 때문에 그 이하의 값을 받으면 Error를 던져준다.
        if (id <= 0) {
            log.error("Error: [TodoService][deleteTodo]");
            return;
        }

        // DAO에 있는 데이터 삭제해주는 기능 호출
        todoDao.deleteTodo(id);
    }
}
