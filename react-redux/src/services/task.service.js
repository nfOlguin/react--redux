import http from "../http-common";

class TaskDataService {
    getAll() {
        return http.get("/all");
    }

    get(identificador) {
        return  http.get(`/${identificador}`);
    }

    create(data) {
        return http.post("/create", data);
    }

    update(identificador, data) {
        return http.put(`/${identificador}`, data);
    }

    delete(identificador) {
        return http.delete(`/${identificador}`);
    }


    findById(identificador) {
        return http.get(`/${identificador}`);
    }
}

export default new TaskDataService();