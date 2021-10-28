package com.aaron.todolist.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Aaron
 * @date 2021/3/8 20:26
 */
@Data
@Entity
@Table(name = "item")
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;

    private String itemContent;

    private Integer status;

    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date createTime;

    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    private Date updateTime;


}
