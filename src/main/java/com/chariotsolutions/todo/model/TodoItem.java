package com.chariotsolutions.todo.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Document(collection = "TodoItem")
public class TodoItem {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Id
    private String id;
    @Field("description")
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @Field("dueDateTime")
    private LocalDateTime dueDateTime;

    public TodoItem() {}

    @PersistenceConstructor
    public TodoItem(String description, LocalDateTime dueDateTime) {
        this.description = description;
        this.dueDateTime = dueDateTime;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    @Override
    public String toString() {
        return String.format(
                "TodoItem[id=%s, description=%s, dueDateTime=%s]",
                id, description, dueDateTime.format(formatter));
    }
}
