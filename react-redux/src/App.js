import React, {Component} from 'react';
import "bootstrap/dist/css/bootstrap.min.css";
import {Link, Route, Switch} from "react-router-dom";
import { BrowserRouter as Router } from 'react-router-dom';

import AddTask from "./components/add-task.component";
import Task from "./components/task.component";
import TaskList from "./components/task-list.component";
class App extends Component {

    render () {
        return (
            <Router>

                <nav className="navbar navbar-expand navbar-dark bg-dark">
                    <Link to={"/tasks"} className="navbar-brand">
                        Gestión de areas con REDUX
                    </Link>
                    <div className="navbar-nav mr-auto">
                        <li className="nav-item">
                            <Link to={"/tasks"} className="nav-link">
                                Tareas
                            </Link>
                        </li>
                        <li className="nav-item">
                            <Link to={"/add"} className="nav-link">
                                Agregar
                            </Link>
                        </li>
                    </div>
                </nav>

                <div className="container mt-3">
                    <Switch>
                        <Route exact path={["/", "/tasks"]} component={TaskList}/>
                        <Route exact path="/add" component={AddTask}/>
                        <Route path="/tasks/:identificador" component={Task}/>
                    </Switch>
                </div>
            </Router>
        )
    }
};

export default App;
