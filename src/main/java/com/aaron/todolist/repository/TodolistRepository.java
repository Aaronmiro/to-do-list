package com.aaron.todolist.repository;

import com.aaron.todolist.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * @author Aaron
 * @date 2021/3/8 21:02
 */
public interface TodolistRepository extends JpaRepository<TodoItem, Integer> {
    List<TodoItem> findAllByCreateTimeAndStatusIsNot(Date createTime, Integer isDeleted);

    List<TodoItem> findByItemContentAndCreateTime(String content, Date createTime);
}
