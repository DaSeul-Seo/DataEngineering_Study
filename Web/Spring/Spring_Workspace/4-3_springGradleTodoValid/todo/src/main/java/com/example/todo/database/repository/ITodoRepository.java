package com.example.todo.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo.model.entity.TodoEntity;

public interface ITodoRepository extends JpaRepository<TodoEntity, Long> {
    
}
