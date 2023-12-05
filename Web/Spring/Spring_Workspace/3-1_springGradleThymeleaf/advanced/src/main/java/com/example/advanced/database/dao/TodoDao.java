package com.example.advanced.database.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.advanced.database.repository.ITodoRepository;
import com.example.advanced.model.entity.TodoEntity;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@Service
public class TodoDao {
    @Autowired
    private ITodoRepository todoRepository;

    public List<TodoEntity> selectAll() {
        log.info("[TodoDao][selectAll] findAll() Start");
        List<TodoEntity> todoList = todoRepository.findAll();
        log.info("todoList: " + todoList.size());
        return todoList;
    }

    public void insertData(TodoEntity todoEntity) {
        log.info("[TodoDao][selectAll] save(entity) Start");
        log.info("todoEntity: " + todoEntity.toString());
        
        todoRepository.save(todoEntity);
    }

    public void updateData(TodoEntity todoEntity) {
        log.info("[TodoDao][selectAll] saved(entity) Start");
        // 기존 저장 데이터 가져오기
        TodoEntity savedData = todoRepository.getReferenceById(todoEntity.getId());
        // if (todoEntity.getTitle() != null) {
        //     savedData.setTitle(todoEntity.getTitle());
        // }
        if (todoEntity.getStatus() != null) {
            savedData.setStatus(todoEntity.getStatus());
        }
        log.info("savedData: " + savedData.toString());
        todoRepository.save(savedData);
    }

    public void deleteData(long id) {
        log.info("[TodoDao][selectAll] delete(entity) Start");
        TodoEntity savedData = todoRepository.getReferenceById(id);
        
        log.info("savedData: " + savedData.toString());
        todoRepository.delete(savedData);
    }
}
