import React from 'react';
import './App.css';
import About from "./components/About";
import Home from "./components/Home";
import Lists from "./components/Lists";
import Login from "./components/Login";
import Navigation from "./components/Navigation";
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

function App() {
  return (
    <Router>
      <div className="App">
        <Navigation />
        <Switch>
          <Route path="/" exact component={Home} />
          <Route path="/lists" component={Lists} />
          <Route path="/login" component={Login} />
          <Route path="/about" component={About} />
        </Switch>
      </div>
    </Router>
  );
}
export default App;
