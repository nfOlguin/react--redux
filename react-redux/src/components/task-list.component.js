import React, { Component } from "react";
import { connect } from "react-redux";
import { retrieveTasks, findTaskById } from "../actions/tasks";
import {Link} from "react-router-dom";

class TasksList extends Component {
    constructor(props) {
        super(props);
        this.onChangeSearchById = this.onChangeSearchById.bind(this);
        this.refreshData = this.refreshData.bind(this);
        this.setActiveTask = this.setActiveTask.bind(this);
        this.findById = this.findById.bind(this);

        this.state = {
            currentTask: null,
            currentIndex: -1,
            searchId: "",
        };
    }

    componentDidMount() {
        this.props.retrieveTasks();
    }

    onChangeSearchById(e) {
        const searchId = e.target.value;
        this.setState({
            searchId: searchId,
        });
    }

    refreshData() {
        this.setState({
            currentTask: null,
            currentIndex: -1,
        });
    }

    setActiveTask(task, index) {
        this.setState({
            currentTask: task,
            currentIndex: index,
        });
    }

    findById() {
        this.refreshData();
        if(this.state.searchId === ""){
            this.props.retrieveTasks();
        }else {
            this.props.findTaskById(this.state.searchId);
        }
    }

    render() {
        const { searchId, currentTask, currentIndex } = this.state;
        const { tasks } = this.props;

        return (
            <div className="list row">
                <div className="col-md-8">
                    <div className="input-group mb-3">
                        <input
                            type="text"
                            className="form-control"
                            placeholder="Buscar por identificador"
                            value={searchId}
                            onChange={this.onChangeSearchById}
                        />
                        <div className="input-group-append">
                            <button
                                className="btn btn-outline-secondary"
                                type="button"
                                onClick={this.findById}
                            >Buscar
                            </button>
                        </div>
                    </div>
                </div>
                <div className="col-md-6">
                    <h4>Listado de tareas</h4>

                    <ul className="list-group">
                        {Array.isArray(tasks) && tasks.length > 0 ? (
                            tasks.map((task, index) => (
                                <li
                                    className={"list-group-item " + (index === currentIndex ? "active" : "")}
                                    onClick={() => this.setActiveTask(task, index)}
                                    key={index}
                                >
                                    {task.identificador} - {task.descripcion}
                                </li>
                            ))
                        ) : (
                            <li className={"list-group-item active"}
                                onClick={() => this.setActiveTask(tasks, 1)}>
                                {tasks ? (
                                    <>
                                        <span>{tasks.identificador} - {tasks.descripcion}</span>
                                    </>
                                ) : (
                                    'No existe tarea disponible'
                                )}
                            </li>
                        )}
                    </ul>

                </div>
                <br/>
                <div className="col-md-6">
                    {currentTask ? (
                        <div>
                            <h4>Resumen:</h4>
                            <div>
                                <label>
                                    <strong>Identificador:</strong>
                                </label>{" "}
                                {currentTask.identificador}
                            </div>
                            <div>
                                <label>
                                    <strong>Descripcion:</strong>
                                </label>{" "}
                                {currentTask.descripcion}
                            </div>
                            <div>
                                <label>
                                    <strong>vigencia:</strong>
                                </label>{" "}
                                {currentTask.vigente ? "Activo" : "Inactivo"}
                            </div>
                            <div>
                                <label>
                                    <strong>Fecha de creación:</strong>
                                </label>{" "}
                                {currentTask.fechaCreacion}
                            </div>

                            <Link
                                to={"/tasks/" + currentTask.identificador}
                                className="badge badge-warning"
                            >
                                Editar
                            </Link>
                        </div>
                    ) : (
                        <div>
                            <br/>
                            {tasks && Array.isArray(tasks)? (<p>Seleccione una tarea para ver el detalle:</p>): (<div></div>)}
                        </div>
                        )
                    }
                </div>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        tasks: state.tasks,
    };
};

export default connect(mapStateToProps, { retrieveTasks, findTaskById })(TasksList);