package com.chariotsolutions.todo.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
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

    @Field("dueDateTime")
    private LocalDateTime dueDateTime;

    public TodoItem() {}

    @PersistenceConstructor
    public TodoItem(String description, LocalDateTime dueDateTime) {
        this.description = description;
        this.dueDateTime = dueDateTime;
    }

    @ApiModelProperty(notes = "The id of the todo item", required = true)
    public String getId() {
        return id;
    }

    @ApiModelProperty(notes = "The description of the todo item", required = true)
    public String getDescription() {
        return description;
    }

    @ApiModelProperty(notes = "The due date time of the todo item", required = true,
            example = "2016-08-09T12:00:00.000")
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
