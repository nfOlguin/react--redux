import axios from "axios";

const instance = axios.create({
    baseURL: 'http://localhost:8080/api/tasks',
    headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
    },
});

export default instance;