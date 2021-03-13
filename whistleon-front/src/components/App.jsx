import React from 'react';
import { Route } from 'react-router-dom';
import HomeView from './HomeView.jsx';
import LoginView from './LoginView.jsx';
import SignupView from './SignupView.jsx';

const App = () => {
  return (
    <div>
      <Route path="/" component={HomeView} exact />
      <Route path="/login" component={LoginView} />
      <Route path="/signup" component={SignupView} />
    </div>
  );
};

export default App;