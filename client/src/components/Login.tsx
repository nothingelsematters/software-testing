import React from "react";
import "../App.css";
import Configuration from "../Configuration";
import { withRouter, RouteComponentProps } from "react-router-dom";

class Login extends React.Component<{ submitLogin: () => void } & RouteComponentProps, { login: string, password: string, result: string | null }> {
  constructor(props: any) {
    super(props);
    this.state = {
      login: "",
      password: "",
      result: null
    };
  }

  backendAuthorize = (e: any) => {
    e.preventDefault();

    const data = {
      login: this.state.login,
      password: this.state.password
    };

    fetch(`${Configuration.apiUrl}authorize`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(data)
    })
      .then(res => res.text())
      .then(res => this.setState({ result: res }))
      .catch(err => {
        console.log(err);
        this.setState({ result: "failure" });
      });
  };

  render() {
    return (
      <div className="main">
        <h1 className="title">Login page</h1>
        <form className="form" onSubmit={this.backendAuthorize}>
          <input className="input" id="login" type="text" name="login" placeholder="login"
            required minLength={4} maxLength={20} pattern="[A-Za-z0-9_\-]+"
            onChange={e => this.setState({ login: e.target.value })} />

          <input className="input" id="password" type="password" name="pass" placeholder="password"
            required minLength={5} maxLength={40} pattern="[A-Za-z0-9_\-!@$%^&*]+"
            onChange={e => this.setState({ password: e.target.value })} />

          <button className="button" onClick={this.props.submitLogin}>login</button>
        </form>
        {this.state.result ? <div key={this.state.result}>{this.state.result}</div> : null}
      </div>
    );
  }
}

export default withRouter(Login);
