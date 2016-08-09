package com.chariotsolutions.todo.repository;

import com.chariotsolutions.todo.app.Application;
import com.chariotsolutions.todo.model.TodoItem;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Ignore
public class TodoItemRepositoryTest {

    @Autowired
    private TodoItemRepository itemRepo;

    @Before
    public void setUp() {
        itemRepo.deleteAll();
        itemRepo.insert(new TodoItem("Pay mortgage bill", LocalDateTime.now()));

    }

    @Test
    public void testFindAll() {
        List<TodoItem> items = itemRepo.findAll();
        assert(items.size() == 1);
    }

}
