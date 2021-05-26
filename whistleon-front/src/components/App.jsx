import React from 'react';
import { BrowserRouter, Route } from 'react-router-dom';
import HomeView from './HomeView.jsx';
import LoginView from './LoginView.jsx';
import SignupView from './SignupView.jsx';

const App = () => {
  return (
    <BrowserRouter>
      <Route path="/" component={HomeView} exact />
      <Route path="/login" component={LoginView} />
      <Route path="/signup" component={SignupView} />
    </BrowserRouter>
  );
};

export default App;
