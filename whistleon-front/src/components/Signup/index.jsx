import React, { useRef, useState, useReducer } from 'react';
import PostCode from '@/components/PostCode';
import Email from './Email';
import Name from './Name';
import Nickname from './Nickname';
import PhoneNum from './PhoneNum';
import Password from './Password';
import Physical from './Physical';
import Birthday from './Birthday';
import Location from './Location';
import Position from './Position';
import SelfDescription from './SelfDescription';
import Buttons from './Buttons';
import './signup.scoped.scss';

function signupReducer(state, action) {
  return {
    ...state,
    [action.name]: action.value
  };
}

const Signup = () => {
  const [state, dispatch] = useReducer(signupReducer, {
    email: '',
    birthday: '',
    sido: '',
    sigungu: '',
    roadAddress: '',
    zonecode: '',
    password: '',
    passwordCheck: '',
    name: '',
    nickname: '',
    phoneNum: '',
    height: '',
    weight: '',
    position1: '',
    position2: '',
    description: ''
  });

  const { email, birthday, password, passwordCheck, name, nickname, phoneNum, height, weight, position1, position2, description } = state;

  const [location, setLocation] = useState('');
  const [onAddress, setOnAddress] = useState(false);
  const signupForm = useRef(null);

  const onSubmit = (e) => {
    e.preventDefault();
    console.log(state);
  };

  return (
    <>
      <PostCode autoClose={true} animation={true} onAddress={onAddress} setOnAddress={setOnAddress} setLocation={setLocation} dispatch={dispatch} />
      <div className="signup__wrapper">
        <h1 className="signup__text--title">회원가입</h1>
        <form className="signup__form" noValidate ref={signupForm} onSubmit={onSubmit}>
          <ul className="signup__info">
            <li className="signup__info__input">
              <Email email={email} dispatch={dispatch} />
              <Name name={name} dispatch={dispatch} />
            </li>
            <li className="signup__info__input">
              <Nickname nickname={nickname} dispatch={dispatch} />
              <PhoneNum phoneNum={phoneNum} dispatch={dispatch} />
            </li>
            <li className="signup__info__input">
              <Password password={password} passwordCheck={passwordCheck} dispatch={dispatch} />
            </li>
            <li className="signup__info__input">
              <Birthday birthday={birthday} dispatch={dispatch} />
              <Location location={location} onFocus={() => setOnAddress(true)} />
            </li>
            <li className="signup__info__input signup__info__input--double">
              <Physical weight={weight} height={height} dispatch={dispatch} />
              <Position position1={position1} position2={position2} dispatch={dispatch} />
            </li>
            <li className="signup__info__input">
              <SelfDescription description={description} dispatch={dispatch} />
            </li>
            <li className="signup__info__input signup__info__input--btns">
              <Buttons />
            </li>
          </ul>
        </form>
      </div>
    </>
  );
};

export default React.memo(Signup);
