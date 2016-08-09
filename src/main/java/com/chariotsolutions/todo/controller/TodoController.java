package com.chariotsolutions.todo.controller;


import com.chariotsolutions.todo.model.TodoItem;
import com.chariotsolutions.todo.repository.TodoItemRepository;
import com.codahale.metrics.Counter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MetricRegistry metricRegistry;
    private Meter requestMeter;

    @Autowired
    private TodoItemRepository itemRepo;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        logger.debug("Todo item index endpoint invoked.");
        return "My first Spring Boot based microservice!";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<TodoItem> findAll() {
        requestMeter.mark();
        //counter.increment("controller.todo.list.requests");
        return itemRepo.findAll();
    }

    @PostConstruct
    public void postConstruct() {
        requestMeter = metricRegistry.meter("controller.todo.requests");
    }
}
