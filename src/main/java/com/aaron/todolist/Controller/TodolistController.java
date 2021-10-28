package com.aaron.todolist.Controller;

import com.aaron.todolist.entity.TodoItem;
import com.aaron.todolist.service.TodolistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Aaron
 * @date 2021/3/7 18:50
 */
@CrossOrigin
@RestController
public class TodolistController {

    @Autowired
    private TodolistService todolistService;

    @GetMapping("/test")
    public Map<String, List<String>> test() {
        Map<String, List<String>> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("Welcome");
        list.add("To");
        list.add("Todolist");
        map.put("data", list);
        System.out.println(map);
        return map;
    }

    @GetMapping("/load")
    public Map<String, List<TodoItem>> loadData() {
        Map<String, List<TodoItem>> map = new HashMap<>();
        map.put("data", todolistService.loadItems());
        return map;
    }

    @GetMapping("/addOne")
    public Map<String, TodoItem> addOne(String content) {
        TodoItem todoItem = new TodoItem();
        System.out.println(content);
        todoItem.setItemContent(content);
        todoItem.setStatus(0);
        Map<String, TodoItem> map = new HashMap<>();
        map.put("data", todolistService.addOne(todoItem));
        return map;
    }

    @DeleteMapping("/deleteOne")
    public Map<String, String> deleteOne(String content) {
        TodoItem todoItem = new TodoItem();
        todoItem.setItemContent(content);
        Map<String, String> map = new HashMap<>();
        map.put("code", todolistService.deleteOne(todoItem) ? "200" : "50000");
        return map;
    }

    @PutMapping("/oneDone")
    public Map<String, String> oneDone(String content) {
        TodoItem todoItem = new TodoItem();
        todoItem.setItemContent(content);
        Map<String, String> map = new HashMap<>();
        map.put("code", todolistService.doneOne(todoItem) ? "200" : "50000");
        return map;
    }

    @PutMapping("/oneUnDone")
    public Map<String, String> oneUnDone(String content) {
        TodoItem todoItem = new TodoItem();
        todoItem.setItemContent(content);
        Map<String, String> map = new HashMap<>();
        map.put("code", todolistService.oneUnDone(todoItem) ? "200" : "50000");
        return map;
    }
}
