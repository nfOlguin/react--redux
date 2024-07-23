package com.crud.springbootmysql.service.impl;

import com.crud.springbootmysql.model.TaskEntity;
import com.crud.springbootmysql.repository.ITaskRepository;
import com.crud.springbootmysql.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements ITaskService {

    public static String TaskDateFormat = "yyyy-MM-dd HH:mm:ss";

    @Autowired
    private ITaskRepository taskRepository;

    public List<TaskEntity> getAll() {
        return taskRepository.findAll();
    }

    public Optional<TaskEntity> findById(Long id) {
        return taskRepository.findById(id);
    }

    public int create(TaskEntity taskEntity) {
        taskEntity.setFechaCreacion(LocalDateTime.now());
        return taskRepository.save(taskEntity);
    }

    public int update(TaskEntity taskEntity) {
        return taskRepository.update(taskEntity);
    }

    public int deleteById(Long id) {
        return taskRepository.deleteById(id);
    }
}
