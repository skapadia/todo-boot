package com.chariotsolutions.todo.repository;


import com.chariotsolutions.todo.model.TodoItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoItemRepository extends MongoRepository<TodoItem, String> {
}
