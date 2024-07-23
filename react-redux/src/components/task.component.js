import React, { Component } from "react";
import { connect } from "react-redux";
import { updateTask, deleteTask } from "../actions/tasks";
import TaskService from "../services/task.service";

class Task extends Component {
    constructor(props) {
        super(props);
        this.onChangeVigente = this.onChangeVigente.bind(this);
        this.onChangeDescripcion = this.onChangeDescripcion.bind(this);
        this.getTask = this.getTask.bind(this);
        this.updateContent = this.updateContent.bind(this);
        this.removeTask = this.removeTask.bind(this);

        this.state = {
            currentTask: {
                identificador: null,
                vigente: false,
                descripcion: "",
                fechaCreacion: "",
            },
            message: "",
        };
    }

    componentDidMount() {
        const test = this.props;
        this.getTask(this.props.match.params.identificador);
    }

    onChangeVigente(e) {
        const vigente = e.target.value === "true";

        this.setState(prevState=> {
            return {
                currentTask: {
                    ...prevState.currentTask,
                    vigente: vigente,
                },
            };
        });
    }

    onChangeDescripcion(e) {
        const descripcion = e.target.value;

        this.setState((prevState) => ({
            currentTask: {
                ...prevState.currentTask,
                descripcion: descripcion,
            },
        }));
    }

    getTask(identificador) {
        TaskService.get(identificador)
            .then((response) => {
                this.setState({
                    currentTask: response.data,
                });
                console.log(response.data);
            })
            .catch((e) => {
                console.log(e);
            });
    }

    updateContent() {
        this.props.updateTask(this.state.currentTask.identificador, this.state.currentTask)
            .then((reponse) => {
                console.log(reponse);

                this.setState({ message: "The task was updated successfully!" });
            })
            .catch((e) => {
                console.log(e);
            });
    }

    removeTask() {
        this.props.deleteTask(this.state.currentTask.identificador)
            .then(() => {
                this.props.history.push("/tasks");
            })
            .catch((e) => {
                console.log(e);
            });
    }

    render() {
        const { currentTask } = this.state;

        return (
            <div>
                {currentTask ? (
                    <div className="edit-form">
                        <h4>Detalle tarea id: {currentTask.identificador}</h4>
                        <form>
                            <div className="form-group">
                                <label htmlFor="descripcion">Descripcion</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="descripcion"
                                    value={currentTask.descripcion}
                                    onChange={this.onChangeDescripcion}
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="vigente">Vigente</label>
                                <select
                                    className="form-control"
                                    id="vigente"
                                    value={currentTask.vigente}
                                    onChange={this.onChangeVigente}
                                    name="vigente"
                                >
                                    <option value={true}>Activo</option>
                                    <option value={false}>Inactivo</option>
                                </select>
                            </div>
                            <div className="form-group">
                                <label>
                                    <label htmlFor="fechaCreacion">Fecha de creación</label>
                                </label>
                                {currentTask.fechaCreacion}
                            </div>
                        </form>


                        <button
                            className="badge badge-danger mr-2"
                            onClick={this.removeTask}
                        >
                            Delete
                        </button>

                        <button
                            type="submit"
                            className="badge badge-success"
                            onClick={this.updateContent}
                        >
                            Update
                        </button>
                        <p>{this.state.message}</p>
                    </div>
                ) : (
                    <div>
                        <br />
                        <p>Selecciona una tarea:</p>
                    </div>
                )}
            </div>
        );
    }
}

export default connect(null, { updateTask, deleteTask })(Task);