package com.chariotsolutions.todo.app;


import com.chariotsolutions.todo.repository.TodoItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "com.chariotsolutions")
@EnableMongoRepositories(basePackages="com.chariotsolutions.todo.repository")
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private TodoItemRepository todoItemsRepository;

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        logger.info("TODO Microservice started.");
    }
}
