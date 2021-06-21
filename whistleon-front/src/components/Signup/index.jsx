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
  const [email, setEmail] = useState('');
  const [name, setName] = useState('');
  const [password, setPassword] = useState('');
  const [passwordCheck, setPasswordCheck] = useState('');
  const [birthday, setBirthday] = useState('');
  const [phoneNum, setPhoneNum] = useState('');
  const [location, setLocation] = useState('');
  const [height, setHeight] = useState('');
  const [weight, setWeight] = useState('');
  const [nickname, setNickname] = useState('');
  const [mainPosition, setMainPosition] = useState('');
  const [subPosition, setSubPosition] = useState('');
  const [description, setDescription] = useState('');
  const [onAddress, setOnAddress] = useState(false);
  const signupForm = useRef(null);

  const onSubmit = (e) => {
    e.preventDefault();
  };

  return (
    <>
      <PostCode autoClose={true} animation={true} onAddress={onAddress} setOnAddress={setOnAddress} inputLocationToForm={setLocation} />
      <div className="signup__wrapper">
        <h1 className="signup__text--title">회원가입</h1>
        <form className="signup__form" noValidate ref={signupForm} onSubmit={onSubmit}>
          <ul className="signup__info">
            <li className="signup__info__input">
              <Email email={email} onInput={({ target: { value } }) => setEmail(value)} />
              <Name name={name} onInput={({ target: { value } }) => setName(value)} />
            </li>
            <li className="signup__info__input">
              <Nickname nickname={nickname} onInput={({ target: { value } }) => setNickname(value)} />
              <PhoneNum phoneNum={phoneNum} onInput={({ target: { value } }) => setPhoneNum(value)} />
            </li>
            <li className="signup__info__input">
              <Password
                password={password}
                passwordCheck={passwordCheck}
                onPasswordInput={({ target: { value } }) => setPassword(value)}
                onPasswordCheckInput={({ target: { value } }) => setPasswordCheck(value)}
              />
            </li>
            <li className="signup__info__input">
              <Birthday birthday={birthday} onInput={({ target: { value } }) => setBirthday(value)} />
              <Location location={location} onFocus={() => setOnAddress(true)} />
            </li>
            <li className="signup__info__input signup__info__input--double">
              <Physical weight={weight} height={height} onInputWeight={({ target: { value } }) => setWeight(value)} onInputHeight={({ target: { value } }) => setHeight(value)} />
              <Position
                mainPosition={mainPosition}
                subPosition={subPosition}
                onChangeMainPosition={({ target: { value } }) => setMainPosition(value)}
                onChangeSubPosition={({ target: { value } }) => setSubPosition(value)}
              />
            </li>
            <li className="signup__info__input">
              <SelfDescription description={description} onInput={({ target: { value } }) => setDescription(value)} />
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
