package com.chariotsolutions.todo.app;


import com.chariotsolutions.todo.model.TodoItem;
import com.chariotsolutions.todo.repository.TodoItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.ExportMetricWriter;
import org.springframework.boot.actuate.metrics.jmx.JmxMetricWriter;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.jmx.export.MBeanExporter;

import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication(scanBasePackages = "com.chariotsolutions")
@EnableMongoRepositories(basePackages="com.chariotsolutions.todo.repository")
@Import(JmxAutoConfiguration.class)
public class Application implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private TodoItemRepository todoItemsRepository;

    @Bean
    @ExportMetricWriter
    MetricWriter metricWriter(MBeanExporter exporter) {
        return new JmxMetricWriter(exporter);
    }

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
}
