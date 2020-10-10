import React from 'react';
import '../App.css';
import { Link } from 'react-router-dom';

function Navigation() {
  return (
    <nav>
      <ul className="navigation-links">
        <Link to='/' className="links">
          <li>Home</li>
        </Link>
        <Link to='/login' className="links">
          <li>Login</li>
        </Link>
        <Link to='/about' className="links">
          <li>About</li>
        </Link>
      </ul>
    </nav>
  );
}

export default Navigation;
