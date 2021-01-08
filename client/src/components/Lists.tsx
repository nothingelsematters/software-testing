import React from "react";
import "../App.css";
import { withRouter, RouteComponentProps } from "react-router-dom";

type ToDoList = {
  id: number,
  name: string,
  tasks: [
    {
      id: number,
      parentId: number,
      description: string,
      completed: boolean
    }
  ]
};

class Lists extends React.Component<
  RouteComponentProps,
  { result: string | [ToDoList], listName: string, taskName: string }
  > {

  constructor(props: any) {
    super(props);
    this.state = {
      result: "loading...",
      listName: "",
      taskName: ""
    };

    fetch("http://localhost:8080/api/v1/", { method: "GET" })
      .then(res => res.text())
      .then(res => this.setState({ result: JSON.parse(res) as [ToDoList] }))
      .catch(
        function (this: Lists, err: any) {
          console.log(err);
          this.setState({ result: `Failure: ${err}` });
        }.bind(this));
  }

  performRequest(url: string, props: RequestInit): (e: any) => void {
    return (e: any) => {
      e.preventDefault();
      (async () => {
        await fetch(url, props).catch(err => console.log(err));
        window.location.reload();
      })();
    };
  }

  handleDelete(id: number): (e: any) => void {
    return this.performRequest(`http://localhost:8080/api/v1/delete/${id}`, { method: "POST" });
  }

  handleCheck(id: number): (e: any) => void {
    return this.performRequest(`http://localhost:8080/api/v1/check/${id}`, { method: "POST" });
  }

  handleAddList(): (e: any) => void {
    return this.performRequest(`http://localhost:8080/api/v1?listName=${this.state.listName}`, { method: "POST" });
  }

  handleAddTask(id: number): (e: any) => void {
    return this.performRequest(
      `http://localhost:8080/api/v1/${id}/add`,
      {
        method: "POST",
        body: this.state.taskName
      }
    );
  };

  render() {
    let res = this.state.result;
    let content;

    if (typeof res == "string") {
      content = res;
    } else {
      content = res.map(list => (
        <ul>
          <li>
            {list.name}
            <button id="check" onClick={this.handleDelete(list.id).bind(this)}>delete list</button>
            <ul>
              {
                list.tasks.map(task =>
                  <li>
                    {
                        task.completed
                          ? "✔️"
                          : <button id="check" onClick={this.handleCheck(task.id).bind(this)}>✖️</button>
                    }
                    {task.description}
                  </li>
                )
              }
            </ul>

            <form className="form" onSubmit={this.handleAddTask(list.id).bind(this)}>
              <input className="input" id="new-task" type="text" name="new-task" placeholder="list name"
                required minLength={0} maxLength={500} pattern="[A-Za-z0-9_\- ]+"
                onChange={e => this.setState({ taskName: e.target.value })} />
              <button className="button" onClick={this.handleAddTask(list.id).bind(this)}>add task</button>
            </form>
          </li>
        </ul>
      ))
    }

    return (
      <div className="main">
        {content}
        <form className="form" onSubmit={this.handleAddList().bind(this)}>
          <input className="input" id="new-list" type="text" name="new-list" placeholder="list name"
            required minLength={0} maxLength={50} pattern="[A-Za-z0-9_\- ]+"
            onChange={e => this.setState({ listName: e.target.value })} />
          <button className="button" onClick={this.handleAddList().bind(this)}>add list</button>
        </form>
      </div>
    );
  }
}

export default withRouter(Lists);
