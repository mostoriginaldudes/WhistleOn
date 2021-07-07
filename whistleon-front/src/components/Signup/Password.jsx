import React, { useState } from 'react';
import InputUnderline from '../InputUnderline';
import validate from './FormValidation';
import { isError, isValidationError } from '@utils/error';

const Password = () => {
  const [validationMessage, setValidationMessage] = useState(null);
  const [equalMessage, setEqualMessage] = useState(null);
  const [password, setPassword] = useState('');
  const [passwordCheck, setPasswordCheck] = useState('');

  const isUnMatchedPassword = (validationErrorMessage) => {
    validationErrorMessage === '비밀번호가 일치하지 않습니다.' ? setEqualMessage(validationErrorMessage) : setValidationMessage(validationErrorMessage);
  };

  const onInput = () => {
    try {
      const validatedPassword = validate.password(password);
      const validatedPasswordCheck = validate.passwordCheck(password, passwordCheck);

      if (isError(validatedPassword)) {
        throw validatedPassword;
      }
      setValidationMessage(null);

      if (isError(validatedPasswordCheck)) {
        throw validatedPasswordCheck;
      }
      setEqualMessage(null);
    } catch (error) {
      if (isValidationError(error)) {
        isUnMatchedPassword(error.message);
      }
    }
  };

  const passwordInputElementAttr = [
    {
      name: '비밀번호',
      value: password,
      onInput: ({ target: { value } }) => {
        setPassword(value);
        onInput();
      },
      onError: validationMessage
    },
    {
      name: '비밀번호 확인',
      value: passwordCheck,
      onInput: ({ target: { value } }) => {
        setPasswordCheck(value);
        onInput();
      },
      onError: equalMessage
    }
  ];

  return passwordInputElementAttr.map(({ name, value, onInput, onError }, index) => (
    <InputUnderline
      inputAttr={{
        type: 'password',
        required: true,
        value,
        name,
        onInput,
        onError,
        minLength: 8,
        maxLength: 20
      }}
      key={index}
    />
  ));
};

export default React.memo(Password);
