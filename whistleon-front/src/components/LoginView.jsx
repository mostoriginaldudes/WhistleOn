import React, { useState } from 'react';
import InputUnderline from './common/InputUnderline';
import EventButton from './common/EventButton';
import '@/style/login-view.scss';

const LoginView = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const onChange = () => {};

  const onSubmit = (e) => {
    e.preventDefault();
    console.log(e);
  };
  
  return (
    <div className="login__wrapper">
      <h1 className="login__text--title">로그인</h1>
      <form className="login__form" onSubmit={onSubmit}>
        <ul className="login__info">
          <li className="login__info__input">
            <InputUnderline 
              type='email'
              name='이메일'
              required
            />
          </li>
          <li className="login__info__input">
            <InputUnderline 
              type='password'
              name='비밀번호'
              required
            />
          </li>
          <li className="login__info__input">
            {/* <button className="login__info__btn">로그인</button> */}
            <EventButton 
              text='로그인'
              color='yellow'
            />
          </li>
        </ul>
      </form>
    </div>
  );
};

export default LoginView;