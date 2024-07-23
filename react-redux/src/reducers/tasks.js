import {
    CREATE_TASK,
    RETRIEVE_TASKS,
    RETRIEVE_TASKS_BY_ID,
    UPDATE_TASK,
    DELETE_TASK,
}from "../actions/types";

const initialState = [];

function taskReducer(tasks = initialState, action) {
    const { type, payload } = action;

    switch (type) {
        case CREATE_TASK:
            return [...tasks, payload];

        case RETRIEVE_TASKS:
            return payload;

        case RETRIEVE_TASKS_BY_ID:
             return payload;

        case UPDATE_TASK:
            return tasks.map((task) => {
                if (tasks.identificador === payload.identificador) {
                    return {
                        ...task,
                        ...payload,
                    };
                } else {
                    return task;
                }
            });

        case DELETE_TASK:
            return tasks.filter(({ identificador }) => identificador !== payload.identificador);

        default:
            return tasks;
    }
};

export default taskReducer;