package com.crud.springbootmysql.service.impl;

import com.crud.springbootmysql.model.TaskEntity;
import com.crud.springbootmysql.repository.ITaskRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TaskServiceImplTest extends TestCase {

    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private ITaskRepository taskRepository;

    List<TaskEntity> taskEntityList = new ArrayList<>();

    private final TaskEntity taskEntity = new TaskEntity();

    @Before
    public void setupObject() {
        taskEntity.setIdentificador(1L);
        taskEntity.setDescripcion("descripcion");
        taskEntity.setVigente(true);
        taskEntity.setFechaCreacion(LocalDateTime.now());

        taskEntityList.add(taskEntity);
    }

    @Test
    public void deberiaConsultarTodasLasTareas() {
        when(taskRepository.findAll()).thenReturn(taskEntityList);
        Assertions.assertNotNull(taskService.getAll());
    }

    @Test
    public void deberiaConsultarTareaPorId() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(taskEntity));
        Optional<TaskEntity> result = taskService.findById(1L);
        Assertions.assertTrue(result.isPresent());
    }


    @Test
    public void deberiaGuardarTarea() {
        when(taskRepository.save(any(TaskEntity.class))).thenReturn(1);
        int result = taskService.create(taskEntity);
        Assertions.assertEquals(1, result);
    }

    @Test
    public void deberiaActualizarTarea() {
        when(taskRepository.update(any(TaskEntity.class))).thenReturn(1);
        int result = taskService.update(taskEntity);
        Assertions.assertEquals(1, result);
    }

    @Test
    public void deberiaEliminarTareaPorId() {
        when(taskRepository.deleteById(1L)).thenReturn(1);
        int result = taskService.deleteById(1L);
        Assertions.assertEquals(1, result);
    }
}