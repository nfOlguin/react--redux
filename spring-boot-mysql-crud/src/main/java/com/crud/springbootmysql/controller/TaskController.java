package com.crud.springbootmysql.controller;

import com.crud.springbootmysql.dto.TaskDto;
import com.crud.springbootmysql.model.TaskEntity;
import com.crud.springbootmysql.service.ITaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private ITaskService taskService;

    @Autowired
    protected ObjectMapper mapper;


    @ApiOperation(value = "Busca todas las tareas.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Tareas encontradas", response = TaskDto.class, responseContainer = "List"),
            @ApiResponse(code = 204, message = "No se encontraron tareas")})
    @GetMapping(value = "/all")
    public ResponseEntity<List<TaskDto>> getAllTasks() {

        List<TaskDto> responseList = new ArrayList<>();
        List<TaskEntity> taskEntities = taskService.getAll();

        if (taskEntities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<TaskDto> taskDtoList = taskEntities.stream().map(task -> {
            TaskDto taskDto = new TaskDto();
            try {
                BeanUtils.copyProperties(task, taskDto);
            } catch (RuntimeException e) {
                throw new RuntimeException();
            }
            return taskDto;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(taskDtoList, HttpStatus.OK);
    }

    @ApiOperation(value = "Crea una tarea.", notes = "fecha de creación registrada por el sistema")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Tarea creada", response = TaskDto.class),
            @ApiResponse(code = 400, message = "Solicitud inválida")
    })
    @PostMapping(value = "/create")
    public ResponseEntity<TaskDto> create(@RequestBody TaskDto taskDto) throws IOException {
        TaskEntity taskEntity = new TaskEntity();
        BeanUtils.copyProperties(taskDto, taskEntity);

        int result = taskService.create(taskEntity);
        if (result == 1) {
            try {
                BeanUtils.copyProperties(taskEntity, taskDto);
                return new ResponseEntity<>(taskDto, HttpStatus.CREATED);
            } catch (RuntimeException e) {
                throw new RuntimeException();
            }
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Obtiene una tarea por su id.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "tarea encontrada", response = TaskEntity.class),
            @ApiResponse(code = 404, message = "Tarea no encontrada ", response = HttpStatus.class)})
    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskDto> getById(@PathVariable("id") Long id) {
        Optional<TaskEntity> taskEntity = taskService.findById(id);
        return taskEntity.map(task -> {
            TaskDto taskDto = new TaskDto();
            BeanUtils.copyProperties(task, taskDto);
            return new ResponseEntity<>(taskDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ApiOperation(value = "Actualiza una tarea.", notes = "Seleccionada por su id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "tarea Actualizada", response = TaskEntity.class),
            @ApiResponse(code = 404, message = "Tarea no encontrada ", response = HttpStatus.class)})
    @PutMapping(value = "/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable("id") Long id, @RequestBody TaskDto taskDto) {
        Optional<TaskEntity> optionaltask = taskService.findById(id);

        if (optionaltask.isPresent()) {
            TaskEntity taskEntity = optionaltask.get();
            taskEntity.setDescripcion(taskDto.getDescripcion());
            taskEntity.setVigente(taskDto.getVigente());

            int updateResult = taskService.update(taskEntity);
            if (updateResult == 1) {
                try {
                    TaskDto taskDtoResponse = new TaskDto();
                    BeanUtils.copyProperties(taskEntity, taskDtoResponse);
                    return new ResponseEntity<>(taskDtoResponse, HttpStatus.OK);
                } catch (RuntimeException e) {
                    throw new RuntimeException();
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ApiOperation(value = "Elimina una tarea.", notes = "Seleccionada por su id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Tarea eliminada"),
            @ApiResponse(code = 404, message = "Tarea no encontrada"),
            @ApiResponse(code = 417, message = "Error al eliminar tarea no registrada")})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long id) {
        try {
            int result = taskService.deleteById(id);
            if (result == 1) {
                return new ResponseEntity<>("Tarea eliminada", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Error al eliminar tarea", HttpStatus.EXPECTATION_FAILED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar tarea no registrada", HttpStatus.EXPECTATION_FAILED);
        }
    }

}
