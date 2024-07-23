package com.crud.springbootmysql.repository.impl;

import com.crud.springbootmysql.model.TaskEntity;
import com.crud.springbootmysql.repository.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepositoryImpl implements ITaskRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<TaskEntity> findAll() {
        String sql = "SELECT * FROM tarea";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TaskEntity.class));
    }

    public Optional<TaskEntity> findById(Long id) {
        String sql = "SELECT * FROM tarea WHERE identificador = ?";
        try {
            TaskEntity taskEntity = jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(TaskEntity.class));
            return Optional.ofNullable(taskEntity);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public int save(TaskEntity taskEntity) {
        String sql = "INSERT INTO tarea (descripcion, vigente, fecha_creacion) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, taskEntity.getDescripcion(), taskEntity.getVigente(), taskEntity.getFechaCreacion());
    }

    public int update(TaskEntity taskEntity) {
        String sql = "UPDATE tarea SET descripcion = ?, vigente = ? WHERE identificador = ?";
        return jdbcTemplate.update(sql, taskEntity.getDescripcion(), taskEntity.getVigente(), taskEntity.getIdentificador());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM tarea WHERE identificador = ?";
        return jdbcTemplate.update(sql, id);
    }
}