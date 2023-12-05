package com.example.advanced.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.advanced.model.entity.TodoEntity;

// DB Connection 및 쿼리 전달
public interface ITodoRepository extends JpaRepository<TodoEntity, Long>{
    
    // public TodoEntity selectAll();

    // public void insertData(TodoEntity todoEntity);

    // public void updateData(TodoEntity todoEntity);

    // public void deleteData(int id);
}
