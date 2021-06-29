import React, { useState } from 'react';
import InputUnderline from '../InputUnderline';
import EventButton from '../EventButton';
import validate from './FormValidation';
import { isError, isValidationError } from '@utils/error';

const Nickname = () => {
  const [nickname, setNickname] = useState('');
  const [validationMessage, setValidationMessage] = useState(null);

  const onInput = ({ target: { value } }) => {
    try {
      setNickname(value);

      const validatedNickname = validate.nickname(nickname);
      if (isError(validatedNickname)) {
        throw validatedNickname;
      }

      setValidationMessage(null);
    } catch (error) {
      if (isValidationError) {
        setValidationMessage(error.message);
      }
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
          onError: validationMessage
        }}
      />
      <EventButton text="중복 확인" color="gray" />
    </div>
  );
};

export default React.memo(Nickname);
