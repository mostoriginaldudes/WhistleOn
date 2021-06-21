import React, { useRef, useState } from 'react';
import { useHistory } from 'react-router-dom';
import InputUnderline from '@/components/InputUnderline';
import EventButton from '@/components/EventButton';
import './login.scoped.scss';

const Login = () => {
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
              inputAttr={{
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
              inputAttr={{
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
            <div className="login__info__input__wrapper">
              <EventButton
                text="로그인"
                color="yellow"
                eventHandler={() => {
                  loginForm.current.submit();
                }}
              />
              <EventButton text="회원가입" color="gray" eventHandler={() => history.push('/signup')} />
            </div>
          </li>
        </ul>
      </form>
    </div>
  );
};

export default Login;
