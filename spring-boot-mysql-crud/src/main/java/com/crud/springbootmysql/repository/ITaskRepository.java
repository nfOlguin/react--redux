package com.crud.springbootmysql.repository;

import com.crud.springbootmysql.model.TaskEntity;

import java.util.List;
import java.util.Optional;

public interface ITaskRepository {

    public List<TaskEntity> findAll();
    public Optional<TaskEntity> findById(Long id);
    public int save(TaskEntity taskEntity);
    public int update(TaskEntity taskEntity);
    public int deleteById(Long id);
}
