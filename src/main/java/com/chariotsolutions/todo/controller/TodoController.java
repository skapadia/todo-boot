package com.chariotsolutions.todo.controller;


import com.chariotsolutions.todo.model.TodoItem;
import com.chariotsolutions.todo.repository.TodoItemRepository;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    // This can be encapsulated behind a "Metrics Service" that can contain helper methods etc.
    private MetricRegistry metricRegistry;

    // Spring Actuator's CounterService and GaugeService can also be used.
    // The names submitted to GaugeService can be prefixed with meter., histogram., etc. to be
    // recognized by Dropwizard Metrics
    private Meter requestMeter;

    @Autowired
    private TodoItemRepository itemRepo;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        logger.debug("Todo item index endpoint invoked.");
        return "My first Spring Boot based microservice!";
    }

    @ApiOperation(value = "getTodos", nickname = "getTodos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = TodoItem.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<TodoItem> findAll() {
        requestMeter.mark();
        return itemRepo.findAll();
    }

    @PostConstruct
    public void postConstruct() {
        requestMeter = metricRegistry.meter("controller.todo.requests");
    }
}
