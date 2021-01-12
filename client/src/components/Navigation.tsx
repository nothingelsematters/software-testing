import React from 'react';
import '../App.css';
import { Link } from 'react-router-dom';

function Navigation() {
  return (
    <nav>
      <ul className="navigation-links">
        <li>
          <Link to='/' className="links">Home</Link>
        </li>
        <li>
          <Link to='/lists' className="links">Lists</Link>
        </li>
        <li>
          <Link to='/login' className="links">Login</Link>
        </li>
        <li>
          <Link to='/about' className="links">About</Link>
        </li>
      </ul>
    </nav>
  );
}

export default Navigation;
