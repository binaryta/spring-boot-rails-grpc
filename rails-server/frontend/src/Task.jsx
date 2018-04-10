import React from 'react';
import axios from 'axios';
import 'babel-polyfill';

const getCsrfToken = () => {
  return document.getElementsByName("csrf-token")[0].content;
}


axios.defaults.headers['X-CSRF-TOKEN'] = getCsrfToken();

class TasksStore {
  async allTasks() {
    const res = await axios.get("/tasks");
    return res.data;
  }

  async createTask(text) {
    const res = await axios.post("/tasks", {content: text});
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

  componentWillUpdate() {
  }

  onInputTextEnter(e) {
    if (e.keyCode !== 13) return;
    this.store.createTask(this.state.text).then( (task) => {
      this.setState({
        tasks: this.state.tasks.concat(task)
      });
    });
  }

  onInputTextUpdate(e) {
    this.setState({
      text: e.target.value
    })
  }

  render() {
    return (
      <div className="wrapper">
        <h1 className="title">Today's Todo</h1>
        <div className="new-task">
          <input
            placeholder="Enter after you write something to do ...."
            onKeyDown={this.onInputTextEnter.bind(this)}
            onChange={this.onInputTextUpdate.bind(this)}
          />
        </div>
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
