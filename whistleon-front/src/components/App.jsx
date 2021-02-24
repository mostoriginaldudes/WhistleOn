import React from 'react';
import { Route } from 'react-router-dom';
import Home from './Home.jsx';
import Login from './Login.jsx';
import Signup from './Signup.jsx';

const App = () => {
  return (
    <div>
      <Route path="/" component={Home} exact />
      <Route path="/login" component={Login} />
      <Route path="/signup" component={Signup} />
    </div>
  );
};

export default App;