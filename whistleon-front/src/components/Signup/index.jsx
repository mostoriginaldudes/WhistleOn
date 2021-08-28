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
import ValidationError from '@/errors/ValidationError';
import { submitSignupForm } from '@apis';
import './signup.scoped.scss';
import NetworkError from '../../errors/NetworkError';

const signupReducer = (state, action) => ({
  ...state,
  [action.name]: action.value
});

const Signup = () => {
  const [state, dispatch] = useReducer(signupReducer, {
    email: '',
    name: '',
    nickname: '',
    phoneNum: '',
    password: '',
    passwordCheck: '',
    birthday: '',
    sido: '',
    sigungu: '',
    roadAddress: '',
    zonecode: '',
    height: '',
    weight: '',
    position1: '',
    position2: '',
    description: ''
  });

  const { email, birthday, password, passwordCheck, name, nickname, phoneNum, height, weight, position1, position2, description } = state;
  const [location, setLocation] = useState('');
  const [onAddress, setOnAddress] = useState(false);
  const signupFormElement = useRef(null);

  const formToSend = () => {
    const formToSend = { ...state };
    delete formToSend.passwordCheck;
    return formToSend;
  };

  const checkIfAllInfoValid = (form) => {
    try {
      for (const userInfo of form) {
        if (userInfo instanceof ValidationError) {
          throw userInfo;
        }
      }
      return true;
    } catch (error) {
      console.error(error);
    }
  };

  const requestSignup = async (form) => {
    try {
      const response = await submitSignupForm(form);
      console.log(response);
    } catch (error) {
      if (error instanceof NetworkError) {
        
      }
      throw error;
    }
  };

  const onSubmit = (e) => {
    try {
      e.preventDefault();
      const signupForm = formToSend();

      checkIfAllInfoValid(signupForm) && requestSignup(signupForm);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <>
      <PostCode autoClose={true} animation={true} onAddress={onAddress} setOnAddress={setOnAddress} setLocation={setLocation} dispatch={dispatch} />
      <div className="signup__wrapper">
        <h1 className="signup__text--title">회원가입</h1>
        <form className="signup__form" noValidate ref={signupFormElement} onSubmit={onSubmit}>
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
