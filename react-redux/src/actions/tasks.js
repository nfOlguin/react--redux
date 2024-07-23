import {
    CREATE_TASK,
    RETRIEVE_TASKS,
    RETRIEVE_TASKS_BY_ID,
    UPDATE_TASK,
    DELETE_TASK,
}from "./types";

import TaskService from "../services/task.service";

export const createTask = (vigente, descripcion) => async (dispatch) => {
  try {
    const res = await TaskService.create({ vigente, descripcion });

    dispatch({
      type: CREATE_TASK,
      payload: res.data,
    });

    return Promise.resolve(res.data);
  } catch (err) {
    return Promise.reject(err);
  }
};

export const retrieveTasks = () => async (dispatch) => {
  try {
    const res = await TaskService.getAll();

    dispatch({
      type: RETRIEVE_TASKS,
      payload: res.data,
    });
  } catch (err) {
    console.log(err);
  }
};

export const updateTask = (identificador, data) => async (dispatch) => {
  try {
    const res = await TaskService.update(identificador, data);

    dispatch({
      type: UPDATE_TASK,
      payload: data,
    });

    return Promise.resolve(res.data);
  } catch (err) {
    return Promise.reject(err);
  }
};

export const deleteTask = (identificador) => async (dispatch) => {
  try {
    await TaskService.delete(identificador);

    dispatch({
      type: DELETE_TASK,
      payload: { identificador },
    });
  } catch (err) {
    console.log(err);
  }
};


export const findTaskById = (identificador) => async (dispatch) => {
  try {
    const res = await TaskService.get(identificador);
    console.log(res);
    dispatch({
      type: RETRIEVE_TASKS_BY_ID,
      payload: res.data,
    });
  } catch (err) {
    dispatch({
      type: RETRIEVE_TASKS_BY_ID,
      payload: null,
    });
  }
};
