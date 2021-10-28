package com.aaron.todolist.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.aaron.todolist.entity.TodoItem;
import com.aaron.todolist.repository.TodolistRepository;
import com.aaron.todolist.service.TodolistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Aaron
 * @date 2021/3/8 21:12
 */
@Service
public class TodolistServiceImpl implements TodolistService {

    @Autowired
    private TodolistRepository todolistRepository;

    @Override
    public TodoItem addOne(TodoItem todoItem) {
        return todolistRepository.save(todoItem);
    }

    @Override
    public boolean deleteOne(TodoItem todoItem) {
        String content = todoItem.getItemContent();
        String trim = StrUtil.trim(content);
        List<TodoItem> resultList = todolistRepository.findByItemContentAndCreateTime(trim, DateUtil.date());
        if (resultList.isEmpty()) return false;
        else {
            for (TodoItem result : resultList) {
                result.setStatus(2);
                TodoItem saveResult = todolistRepository.save(result);
                System.out.println(saveResult);
            }
            return true;
        }
    }

    @Override
    public boolean doneOne(TodoItem todoItem) {
        String content = todoItem.getItemContent();
        String trim = StrUtil.trim(content);
        List<TodoItem> resultList = todolistRepository.findByItemContentAndCreateTime(trim, DateUtil.date());
        if (resultList.isEmpty()) return false;
        else {
            for (TodoItem result : resultList) {
                result.setStatus(1);
                TodoItem saveResult = todolistRepository.save(result);
                System.out.println(saveResult);
            }
            return true;
        }
    }

    @Override
    public boolean oneUnDone(TodoItem todoItem) {
        String content = todoItem.getItemContent();
        String trim = StrUtil.trim(content);
        List<TodoItem> resultList = todolistRepository.findByItemContentAndCreateTime(trim, DateUtil.date());
        if (resultList.isEmpty()) return false;
        else {
            for (TodoItem result : resultList) {
                result.setStatus(0);
                TodoItem saveResult = todolistRepository.save(result);
                System.out.println(saveResult);
            }
            return true;
        }
    }

    @Override
    public List<TodoItem> loadItems() {
        return todolistRepository.findAllByCreateTimeAndStatusIsNot(DateUtil.date(), 2);
    }
}

