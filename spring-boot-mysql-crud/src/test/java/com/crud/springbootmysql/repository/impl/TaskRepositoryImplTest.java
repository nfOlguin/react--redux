package com.crud.springbootmysql.repository.impl;

import com.crud.springbootmysql.model.TaskEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskRepositoryImplTest {

    @InjectMocks
    private TaskRepositoryImpl taskRepository;

    @Mock
    private JdbcTemplate jdbcTemplate;

    private TaskEntity taskEntity;

    List<TaskEntity> taskEntityList = new ArrayList<>();

    @Before
    public void setup() {
        taskEntity = new TaskEntity();
        taskEntity.setIdentificador(1L);
        taskEntity.setDescripcion("descripcion");
        taskEntity.setVigente(true);
        taskEntity.setFechaCreacion(LocalDateTime.now());

        taskEntityList.add(taskEntity);
    }

    @Test
    public void deberiaConsultarTodasLasTareas() {
        when(jdbcTemplate.query(Mockito.anyString(), (ResultSetExtractor<Object>) ArgumentMatchers.any())).thenReturn(Collections.singletonList(taskEntity));
        Assertions.assertNotNull(taskRepository.findAll());
        Assertions.assertEquals(1, taskEntityList.size());
    }

    @Test
    public void deberiaConsultarTareaPorId() {
        when(jdbcTemplate.queryForObject(Mockito.anyString(), (Object[]) ArgumentMatchers.any(), ArgumentMatchers.any(BeanPropertyRowMapper.class))).thenReturn(taskEntity);
        Optional<TaskEntity> result = taskRepository.findById(1L);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    public void deberiaGuardarTarea() {
        when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyString(), Mockito.anyBoolean(), Mockito.any(LocalDateTime.class)))
                .thenReturn(1);

        int result = taskRepository.save(taskEntity);
        Assertions.assertEquals(1, result);
    }

    @Test
    public void deberiaActualizarTarea() {
        when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyString(), Mockito.anyBoolean(), Mockito.any(LocalDateTime.class)))
                .thenReturn(1);
        int result = taskRepository.save(taskEntity);
        Assertions.assertEquals(1, result);
    }

    @Test
    public void deberiaEliminarTareaPorId() {
        when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(1);
        int result = taskRepository.deleteById(1L);
        Assertions.assertEquals(1, result);
    }
}