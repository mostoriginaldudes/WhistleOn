import React from 'react';
import '../style/style.scss';

const App = () => {
  return (
    <>
      <div>App</div>
      <div>new</div>
      <img src={require('/dist/assets/images/whistleon-logo.png')} alt="whistle-on-logo-image"/>
    </>
  );
};

export default App;