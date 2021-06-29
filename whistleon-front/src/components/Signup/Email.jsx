import React, { useState } from 'react';
import InputUnderline from '../InputUnderline';
import EventButton from '../EventButton';

import validate from './FormValidation';
import { isError, isValidationError } from '@utils/error';

const Email = () => {
  const [email, setEmail] = useState('');
  const [validationMessage, setValidationMessage] = useState(null);

  const onInput = ({ target: { value } }) => {
    try {
      setEmail(value);

      const validatedEmail = validate.email(value);
      if (isError(validatedEmail)) {
        throw validatedEmail;
      }

      setValidationMessage(null);
    } catch (error) {
      if (isValidationError(error)) {
        setValidationMessage(error.message);
      }
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
          onError: validationMessage
        }}
      />
      <EventButton text="이메일 인증" color="gray" eventHandler={checkEmail} />
    </div>
  );
};

export default React.memo(Email);
