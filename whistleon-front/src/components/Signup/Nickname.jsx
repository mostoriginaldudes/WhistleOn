import React, { useState } from 'react';
import InputUnderline from '../InputUnderline';
import EventButton from '../EventButton';
import validate from './FormValidation';
import { isError } from '@utils/error';

const Nickname = () => {
  const [nickname, setNickname] = useState('');
  const [message, setMessage] = useState(null);

  const onInput = ({ target: { value } }) => {
    try {
      setNickname(value);

      const validateNickname = validate.nickname(nickname);
      if (isError(validateNickname)) throw validateNickname;

      setMessage(null);
    } catch ({ message }) {
      setMessage(message);
    }
  };

  return (
    <div className="signup__info__input__wrapper">
      <InputUnderline
        inputAttr={{
          type: 'text',
          name: '닉네임',
          required: true,
          value: nickname,
          onInput,
          onError: message
        }}
      />
      <EventButton text="중복 확인" color="gray" />
    </div>
  );
};

export default React.memo(Nickname);
