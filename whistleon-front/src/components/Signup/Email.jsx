import React, { useState } from 'react';
import InputUnderline from '../InputUnderline';
import EventButton from '../EventButton';

import validate from './FormValidation';
import { isError } from '@utils/error';

const Email = () => {
  const [email, setEmail] = useState('');
  const [message, setMessage] = useState(null);

  const onInput = ({ target: { value } }) => {
    try {
      setEmail(value);

      const validatedEmail = validate.email(email);
      if (isError(validatedEmail)) throw validatedEmail;

      setMessage(null);
    } catch ({ message }) {
      setMessage(message);
    }
  };

  const checkEmail = () => {};

  return (
    <div className="signup__info__input__wrapper">
      <InputUnderline
        inputAttr={{
          type: 'email',
          name: '이메일',
          required: true,
          value: email,
          onInput,
          onError: message
        }}
      />
      <EventButton text="이메일 인증" color="gray" eventHandler={checkEmail} />
    </div>
  );
};

export default React.memo(Email);
