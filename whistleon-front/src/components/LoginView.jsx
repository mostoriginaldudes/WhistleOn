import React, { useRef, useState } from 'react';
import { useHistory } from 'react-router-dom';
import InputUnderline from './common/InputUnderline';
import EventButton from './common/EventButton';
import '@/style/login-view.scss';

const LoginView = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const history = useHistory();
  const loginForm = useRef(null);

  const onSubmit = (e) => {
    e.preventDefault();
    console.log(e);
  };

  return (
    <div className="login__wrapper">
      <h1 className="login__text--title">로그인</h1>
      <form className="login__form" onSubmit={onSubmit} noValidate ref={loginForm}>
        <ul className="login__info">
          <li className="login__info__input">
            <InputUnderline
              inputObj={{
                type: 'email',
                name: '이메일',
                required: true,
                value: email,
                callbacks: {
                  onInput: (e) => {
                    setEmail(e.target.value);
                  }
                }
              }}
            />
          </li>
          <li className="login__info__input">
            <InputUnderline
              inputObj={{
                type: 'password',
                name: '비밀번호',
                required: true,
                value: password,
                callbacks: {
                  onInput: (e) => {
                    setPassword(e.target.value);
                  }
                }
              }}
            />
          </li>
          <li className="login__info__input">
            <EventButton
              text="로그인"
              color="yellow"
              eventHander={() => {
                loginForm.current.submit();
              }}
            />
          </li>
          <li className="login__info__input">
            <EventButton text="회원가입" color="gray" eventHander={history.push('/signup')} />
          </li>
        </ul>
      </form>
    </div>
  );
};

export default LoginView;
