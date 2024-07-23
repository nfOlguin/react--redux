import React, { Component } from "react";
import { connect } from "react-redux";
import { createTask } from "../actions/tasks";

class AddTask extends Component {
    constructor(props) {
        super(props);
        this.onChangeVigente = this.onChangeVigente.bind(this);
        this.onChangeDescripcion = this.onChangeDescripcion.bind(this);
        this.saveTask = this.saveTask.bind(this);
        this.newTask = this.newTask.bind(this);

        this.state = {
            identificador: null,
            vigente: true,
            descripcion: "",
            fechaCreacion: "",

            submitted: false,
        };
    }

    onChangeVigente(e) {
        this.setState({
            vigente: e.target.value,
        });
    }

    onChangeDescripcion(e) {
        this.setState({
            descripcion: e.target.value,
        });
    }

    saveTask() {
        const { vigente, descripcion } = this.state;

        this.props.createTask(vigente, descripcion)
            .then((data) => {
                this.setState({
                    identificador: data.identificador,
                    vigente: data.vigente,
                    descripcion: data.descripcion,
                    fechaCreacion: data.fechaCreacion,

                    submitted: true,
                });
                console.log(data);
            })
            .catch((e) => {
                console.log(e);
            });
    }

    newTask() {
        this.setState({
            identificador: null,
            vigente: "",
            descripcion: "",
            fechaCreacion: false,

            submitted: false,
        });
    }

    render() {
        return (
            <div className="submit-form">
                {this.state.submitted ? (
                    <div>
                        <h4>Tarea agregada con exito!</h4>
                        <button className="btn btn-success" onClick={this.newTask}>
                            Agregar
                        </button>
                    </div>
                ) : (
                    <div>
                        <div className="form-group">
                            <label htmlFor="descripcion">Descripcion</label>
                            <input
                                type="text"
                                className="form-control"
                                id="descripcion"
                                required
                                value={this.state.descripcion}
                                onChange={this.onChangeDescripcion}
                                name="descripcion"
                            />
                        </div>

                        <div className="form-group">
                            <label htmlFor="vigente">Vigente</label>
                            <select
                                className="form-control"
                                id="vigente"
                                value={this.state.vigente}
                                onChange={this.onChangeVigente}
                                name="vigente"
                            >
                                <option value={true}>Activo</option>
                                <option value={false}>Inactivo</option>
                            </select>
                        </div>

                        <button onClick={this.saveTask} className="btn btn-success">
                            Crear
                        </button>
                    </div>
                )}
            </div>
        );
    }
}

export default connect(null, {createTask})(AddTask);