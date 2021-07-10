import React, { useState } from 'react';
import PropTypes from 'prop-types';
import InputUnderline from '../InputUnderline';
import validate from './FormValidation';
import { isError, isValidationError } from '@utils/error';

const Password = ({ password, passwordCheck, dispatch }) => {
  const [validationMessage, setValidationMessage] = useState(null);
  const [equalMessage, setEqualMessage] = useState(null);

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
      label: '비밀번호',
      name: 'password',
      value: password,
      onInput: ({ target }) => {
        dispatch(target);
        onInput();
      },
      onError: validationMessage
    },
    {
      label: '비밀번호 확인',
      name: 'passwordCheck',
      value: passwordCheck,
      onInput: ({ target }) => {
        dispatch(target);
        onInput();
      },
      onError: equalMessage
    }
  ];

  return passwordInputElementAttr.map(({ label, name, value, onInput, onError }, index) => (
    <InputUnderline
      inputAttr={{
        type: 'password',
        required: true,
        value,
        label,
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

Password.propTypes = {
  password: PropTypes.string.isRequired,
  passwordCheck: PropTypes.string.isRequired,
  dispatch: PropTypes.func.isRequired
};

export default React.memo(Password);
