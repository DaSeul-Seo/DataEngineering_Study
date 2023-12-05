package com.example.advanced.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.advanced.database.dao.TodoDao;
import com.example.advanced.model.dto.TodoDto;
import com.example.advanced.model.entity.TodoEntity;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@Service
public class TodoService {
    @Autowired
    private TodoDao todoDao;

    public List<TodoDto> selectAll() {
        log.info("[TodoService][selectAll] selectAll() Start");

        List<TodoEntity> entityList = todoDao.selectAll();

        List<TodoDto> todoList = new ArrayList<>();
        for(TodoEntity entity:entityList) {
            TodoDto dto = new TodoDto(entity.getId(), entity.getTitle(), entity.getStatus());
            todoList.add(dto);
        }

        return todoList;
    }

    public void insertData(TodoDto todoDto) {
        log.info("[TodoService][insertData] insertData() Start");

        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setTitle(todoDto.getTitle());
        todoEntity.setStatus("false");

        todoDao.insertData(todoEntity);
    }

    public void updateData(long id) {
        log.info("[TodoService][updateData] updateData() Start");

        if (id <= 0) {
            log.info("Error : [TodoService][updateData]");
            return;
        }

        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setId(id);
        todoEntity.setStatus("true");

        todoDao.updateData(todoEntity);
    }

    public void deleteData(long id) {
        log.info("[TodoService][deleteData] deleteData() Start");

        if (id <= 0) {
            log.info("Error : [TodoService][deleteData]");
            return;
        }
        
        todoDao.deleteData(id);
    }
}
