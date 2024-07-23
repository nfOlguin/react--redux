package com.crud.springbootmysql.service;

import com.crud.springbootmysql.model.TaskEntity;

import java.util.List;
import java.util.Optional;

public interface ITaskService {
    public List<TaskEntity> getAll() ;

    public Optional<TaskEntity> findById(Long id);

    public int create(TaskEntity taskEntity);

    public int update(TaskEntity taskEntity);

    public int deleteById(Long id);
}
