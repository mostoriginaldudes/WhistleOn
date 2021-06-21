import React, { useRef, useState } from 'react';
import { useHistory } from 'react-router-dom';
import InputUnderline from '@/components/InputUnderline';
import EventButton from '@/components/EventButton';
import PostCode from '@/components/PostCode';
import './signup.scoped.scss';

const Signup = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [passwordCheck, setPasswordCheck] = useState('');
  const [name, setName] = useState('');
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
  const history = useHistory();

  const today = () => {
    const date = new Date();
    return `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`;
  };

  const soccerPosition = ['GK', 'RW', 'CF', 'ST', 'LW', 'CAM', 'CM', 'CDM', 'CB', 'RWB', 'RB', 'LWB', 'LB'];

  return (
    <>
      <PostCode
        autoClose={true}
        animation={true}
        onAddress={onAddress}
        setOnAddress={setOnAddress}
        inputLocationToForm={(location) => {
          setLocation(location);
        }}
      />
      <div className="signup__wrapper">
        <h1 className="signup__text--title">회원가입</h1>
        <form className="signup__form" noValidate ref={signupForm}>
          <ul className="signup__info">
            <li className="signup__info__input">
              <div className="signup__info__input__wrapper">
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
                <EventButton
                  text="이메일 인증"
                  color="gray"
                  eventHandler={(e) => {
                    console.log(e);
                  }}
                />
              </div>
              <InputUnderline
                inputAttr={{
                  type: 'text',
                  name: '이름',
                  required: true,
                  value: name,
                  callbacks: {
                    onInput: (e) => {
                      setName(e.target.value);
                    }
                  }
                }}
              />
            </li>
            <li className="signup__info__input">
              <div className="signup__info__input__wrapper">
                <InputUnderline
                  inputAttr={{
                    type: 'text',
                    name: '닉네임',
                    required: true,
                    value: nickname,
                    callbacks: {
                      onInput: (e) => {
                        setNickname(e.target.value);
                      }
                    }
                  }}
                />
                <EventButton
                  text="중복 확인"
                  color="gray"
                  eventHandler={(e) => {
                    console.log(e);
                  }}
                />
              </div>
              <InputUnderline
                inputAttr={{
                  readOnly: false,
                  type: 'text',
                  name: '연락처 ("-" 없이)',
                  required: true,
                  value: phoneNum,
                  minLength: 10,
                  maxLength: 11,
                  callbacks: {
                    onInput: (e) => {
                      setPhoneNum(e.target.value);
                    }
                  }
                }}
              />
            </li>
            <li className="signup__info__input">
              {[
                {
                  name: '비밀번호',
                  value: password,
                  callbacks: {
                    onInput: (e) => {
                      setPassword(e.target.value);
                    }
                  }
                },
                {
                  name: '비밀번호 확인',
                  value: passwordCheck,
                  callbacks: {
                    onInput: (e) => {
                      setPasswordCheck(e.target.value);
                    }
                  }
                }
              ].map(({ name, callbacks }, index) => (
                <InputUnderline
                  inputAttr={{
                    type: 'password',
                    required: true,
                    name,
                    callbacks,
                    minLength: 8,
                    maxLength: 20
                  }}
                  key={index}
                />
              ))}
            </li>
            <li className="signup__info__input">
              <InputUnderline
                inputAttr={{
                  type: 'date',
                  name: '생년월일',
                  min: '1950-01-01',
                  max: today(),
                  required: true,
                  value: birthday,
                  callbacks: {
                    onInput: (e) => {
                      setBirthday(e.target.value);
                    }
                  }
                }}
              />
              <InputUnderline
                inputAttr={{
                  type: 'text',
                  name: '연고지',
                  readOnly: true,
                  required: true,
                  value: location,
                  callbacks: {
                    onFocus: () => {
                      setOnAddress(true);
                    }
                  }
                }}
              />
            </li>
            <li className="signup__info__input signup__info__input--double">
              <div className="signup__info__input__wrapper">
                <InputUnderline
                  inputAttr={{
                    type: 'number',
                    name: '키 (cm)',
                    min: 100,
                    max: 250,
                    required: true,
                    value: height,
                    callbacks: {
                      onInput: (e) => {
                        setHeight(e.target.value);
                      }
                    }
                  }}
                />
                <InputUnderline
                  inputAttr={{
                    type: 'number',
                    name: '몸무게 (kg)',
                    min: 30,
                    max: 200,
                    required: true,
                    value: weight,
                    callbacks: {
                      onInput: (e) => {
                        setWeight(e.target.value);
                      }
                    }
                  }}
                />
              </div>
              <div className="signup__info__input__wrapper">
                <InputUnderline
                  inputAttr={{
                    type: 'text',
                    name: '선호 포지션 1',
                    required: true,
                    value: mainPosition,
                    list: 'main-position',
                    callbacks: {
                      onChange: (e) => setMainPosition(e.target.value)
                    }
                  }}
                />
                <datalist id="main-position">
                  {soccerPosition.map((pos, index) => (
                    <option value={pos} key={index} />
                  ))}
                </datalist>
                <InputUnderline
                  inputAttr={{
                    type: 'text',
                    name: '선호 포지션 2',
                    required: true,
                    value: subPosition,
                    list: 'sub-position',
                    callbacks: {
                      onChange: (e) => setSubPosition(e.target.value)
                    }
                  }}
                />
                <datalist id="sub-position">
                  {soccerPosition.map((pos, index) => (
                    <option value={pos} key={index} />
                  ))}
                </datalist>
              </div>
            </li>
            <li className="signup__info__input">
              <h3 className="signup__info__input__guide">자기소개</h3>
            </li>

            <li className="signup__info__input">
              <div
                contentEditable
                className="signup__info__input__description"
                onInput={(e) => {
                  setDescription(e.target.textContent);
                }}
                style={{ width: '100%' }}
              ></div>
            </li>
            <li className="signup__info__input signup__info__input--btns">
              {['회원가입', '취소'].map((text, index) => (
                <EventButton
                  text={text}
                  color={text === '취소' ? 'gray' : 'yellow'}
                  eventHandler={() => {
                    history.replace('/');
                  }}
                  key={index}
                />
              ))}
            </li>
          </ul>
        </form>
      </div>
    </>
  );
};

export default Signup;
