import React from 'react';
import PropTypes from 'prop-types';
import InputUnderline from '../InputUnderline';

const Password = ({ password, onPasswordInput, passwordCheck, onPasswordCheckInput }) => {
  const passwordInputElementAttr = [
    {
      name: '비밀번호',
      value: password,
      onInput: onPasswordInput
    },
    {
      name: '비밀번호 확인',
      value: passwordCheck,
      onInput: onPasswordCheckInput
    }
  ];

  return passwordInputElementAttr.map(({ name, callbacks }, index) => (
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
  ));
};

Password.propTypes = {
  password: PropTypes.string.isRequired,
  passwordCheck: PropTypes.string.isRequired,
  onPasswordInput: PropTypes.func.isRequired,
  onPasswordCheckInput: PropTypes.func.isRequired
};

export default Password;
