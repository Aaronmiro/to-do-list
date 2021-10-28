package com.aaron.todolist.service;

import com.aaron.todolist.entity.TodoItem;

import java.util.List;

/**
 * @author Aaron
 * @date 2021/3/8 21:02
 */

public interface TodolistService {

    List<TodoItem> loadItems();

    TodoItem addOne(TodoItem todoItem);

    boolean deleteOne(TodoItem todoItem);

    boolean doneOne(TodoItem todoItem);

    boolean oneUnDone(TodoItem todoItem);
}
