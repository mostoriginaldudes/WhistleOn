import React, { useRef, useState } from 'react';
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

const Signup = () => {
  const [location, setLocation] = useState('');
  const [onAddress, setOnAddress] = useState(false);
  const signupForm = useRef(null);

  const onSubmit = (e) => {
    e.preventDefault();
  };

  return (
    <>
      <PostCode autoClose={true} animation={true} onAddress={onAddress} setOnAddress={setOnAddress} setLocation={setLocation} />
      <div className="signup__wrapper">
        <h1 className="signup__text--title">회원가입</h1>
        <form className="signup__form" noValidate ref={signupForm} onSubmit={onSubmit}>
          <ul className="signup__info">
            <li className="signup__info__input">
              <Email />
              <Name />
            </li>
            <li className="signup__info__input">
              <Nickname />
              <PhoneNum />
            </li>
            <li className="signup__info__input">
              <Password />
            </li>
            <li className="signup__info__input">
              <Birthday />
              <Location location={location} onFocus={() => setOnAddress(true)} />
            </li>
            <li className="signup__info__input signup__info__input--double">
              <Physical />
              <Position />
            </li>
            <li className="signup__info__input">
              <SelfDescription />
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
