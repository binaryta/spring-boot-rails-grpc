import React from 'react';
import axios from 'axios';
import 'babel-polyfill';

class TasksStore {
  async allTasks() {
    const res = await axios.get("/tasks");
    return res.data;
  }
}
export class Tasks extends React.Component {
  constructor() {
    super();
    this.store = new TasksStore();
    this.state = {
      tasks: []
    }
  }

  componentDidMount() {
    this.store.allTasks().then( (tasks) => {
      this.setState({
        tasks: tasks 
      });
    })
  }

  render() {
    return (
      <div className="wrapper">
        <h1 className="title">Today's Todo</h1>
        <section className="tasks">
          {this.state.tasks.filter(task => !task.done).map(task => {
          return (
            <div className="task">
              <p className="task-content">{task.content}</p>
            </div>
          )
          })}
        </section>

        <section className="tasks">
          {this.state.tasks.filter(task => task.done).map(task => {
          return (
            <div className="task">
              <p className="task-content">{task.content}</p>
            </div>
          )
          })}
        </section>
      </div>
    )
  }
}
