package com.example.advanced.database.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.advanced.database.repository.ITodoRepository;
import com.example.advanced.model.entity.TodoEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j  // 로그를 찍는 라이브러리
@Service    // 해당 클래스가 서비스 계층에 속한다는 어노테이션
public class TodoDao {
    
    @Autowired  // DI(의존성 주임) : 자동으로 인스턴스를 생성
    private ITodoRepository todoRepository; // JPA 인터페이스 : 데이터처리를 손쉽게 처리해준다.

    // 전체 데이터 출력
    public List<TodoEntity> selectAll() {
        log.info("[TodoDao][selectAll] Start");
        // JPA에서 전체 데이터를 출력해주는 함수 사용 (findAll)
        List<TodoEntity> todoList = todoRepository.findAll();
        return todoList;
    }

    // 데이터 추가
    public void insertTodo(TodoEntity todoEntity) {
        log.info("[TodoDao][insertTodo] Start");
        // JPA에서 데이터를 추가해주는 함수 사용(save)
        todoRepository.save(todoEntity);
        log.info("Insert Todo : " + todoEntity.toString());
    }

    // 데이터 업데이트
    public void updateTodo(TodoEntity todoEntity) {
        log.info("[TodoDao][updateTodo] Start");
        // 업데이트를 위해 기존에 저장되어 있는 데이터 가지고 오기
        TodoEntity savedData = todoRepository.getReferenceById(todoEntity.getId());

        // 가지고 온 데이터의 status 값이 true일 경우 
        if (todoEntity.getStatus().equals(true)) {
            // status 값을 전달받은 데이터로 변경
            savedData.setStatus(todoEntity.getStatus());
            log.info("update Todo : " + savedData.toString());
            // 변경한 데이터를 업데이트 한다.
            todoRepository.save(savedData);
        }
    }

    // 데이터 삭제
    public void deleteTodo(long id) {
        log.info("[TodoDao][deleteTodo] Start");
        // TodoEntity savedData = todoRepository.getReferenceById(id);
        // JPA에서 데이터를 id 기준으로 삭제해주는 함수 사용(deleteById)
        todoRepository.deleteById(id);
    }
}
