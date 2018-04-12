import React from 'react';
import axios from 'axios';
import 'babel-polyfill';

axios.defaults.headers['X-CSRF-TOKEN'] = document.getElementsByName("csrf-token")[0].content;

const Area = {
  Todo: 1,
  Done: 2
}

class TasksStore {
  async allTasks() {
    const res = await axios.get("/tasks");
    return res.data;
  }

  async createTask(text) {
    const res = await axios.post("/tasks", {content: text});
    return res.data;
  }

  async updateTask(task) {
    const res = await axios.patch(`/tasks/${task.id}`, {done: task.done});
    return res.data;
  }
}

export class Tasks extends React.Component {
  constructor() {
    super();
    this.store = new TasksStore();
    this.dragStartArea = null;
    this.dragTarget = null;
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

  dragStart(e) {
    this.dragTarget = e.target;
    this.dragTarget.style.visibility = "hidden";

    if (this.isContainTodoArea(e)) {
      this.dragStartArea = Area.Todo;
    } else {
      this.dragStartArea = Area.Done;
    }
  }

  dragEnd(targetTask, e) {
    if (this.dragStartArea == Area.Todo && this.isContainDoneArea(e)) {
      this.updateTask(targetTask, true);
    } else if (this.dragStartArea == Area.Done && this.isContainTodoArea(e)) {
      this.updateTask(targetTask, false);
    }
    this.dragTarget.style.visibility = "visible";
  }

  updateTask(targetTask) {
    this.store.updateTask(targetTask).then(updatedTask => {
      const tasks = this.state.tasks.map( task => {
        if (task != targetTask) return task;
        return updatedTask;
      })
      this.setState({ tasks: tasks });
    });
  }

  isContainTodoArea(e) {
    const todoArea = document.getElementsByClassName("todo-tasks")[0];
    const isContainYAxis = (e.pageY <= todoArea.getBoundingClientRect().bottom && e.pageY >= todoArea.getBoundingClientRect().top)
    const isContainXAxis = (e.pageX <= todoArea.getBoundingClientRect().right && e.pageX >= todoArea.getBoundingClientRect().left)
    console.log(e.pageY);
    return isContainYAxis && isContainXAxis;
  }

  isContainDoneArea(e) {
    const doneArea = document.getElementsByClassName("done-tasks")[0];
    const isContainYAxis = (e.pageY <= doneArea.getBoundingClientRect().bottom && e.pageY >= doneArea.getBoundingClientRect().top);
    const isContainXAxis = (e.pageX <= doneArea.getBoundingClientRect().right && e.pageX >= doneArea.getBoundingClientRect().left);
    return isContainYAxis && isContainXAxis;
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
        <section className="tasks todo-tasks">
          <h2 className="tasks-title">Todo</h2>
          {this.state.tasks.filter(task => !task.done).map(task => {
          return (
            <div
              className="task"
              draggable='true'
              onDragStart={this.dragStart.bind(this)}
              onDragEnd={this.dragEnd.bind(this, task)}
            >
              <span className="delete-icon"></span>
              <p className="task-content">{task.content}</p>
            </div>
          )
          })}
        </section>

        <section className="tasks done-tasks">
          <h2 className="tasks-title">Done</h2>
          {this.state.tasks.filter(task => task.done).map(task => {
          return (
            <div
              className="task"
              draggable='true'
              onDragStart={this.dragStart.bind(this)}
              onDragEnd={this.dragEnd.bind(this, task)}
            >
              <p className="task-content">{task.content}</p>
            </div>
          )
          })}
        </section>
      </div>
    )
  }
}
