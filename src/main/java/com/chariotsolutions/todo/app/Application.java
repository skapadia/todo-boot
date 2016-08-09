package com.chariotsolutions.todo.app;


import com.chariotsolutions.todo.model.TodoItem;
import com.chariotsolutions.todo.repository.TodoItemRepository;
import com.codahale.metrics.MetricRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication(scanBasePackages = "com.chariotsolutions")
@EnableMongoRepositories(basePackages="com.chariotsolutions.todo.repository")
@EnableSwagger2
public class Application implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private TodoItemRepository todoItemsRepository;

    @Autowired
    private MetricRegistry metricRegistry;

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        logger.info("TODO Microservice started.");
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("In the CommandLineRunner.run method");

        todoItemsRepository.deleteAll();

        TodoItem item1 = new TodoItem("Get milk and bananas", LocalDateTime.now().plusHours(5));
        TodoItem item2 = new TodoItem("Pick up the dry cleaning", LocalDateTime.now().plusDays(1));
        todoItemsRepository.save(Arrays.asList(item1, item2));
        for (TodoItem item : todoItemsRepository.findAll()) {
            System.out.println(item);
        }
    }

    @Bean
    public Docket swaggerSettings() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("todo")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.chariotsolutions.todo.controller"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Todo Item Spring Boot Microservice")
                .description("Todo Item Spring Boot Microservice")
                .version("0.0.1")
                .build();
    }



}
