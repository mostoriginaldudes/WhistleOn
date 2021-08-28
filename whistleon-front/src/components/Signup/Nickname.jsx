import React, { useState } from 'react';
import PropTypes from 'prop-types';
import InputUnderline from '../InputUnderline';
import EventButton from '../EventButton';
import formValidationUtil from './Validate';
import { isError, isValidationError } from '@utils/error';

const Nickname = ({ nickname, dispatch }) => {
  const [validationMessage, setValidationMessage] = useState(null);
  const onInput = ({ target }) => {
    try {
      dispatch(target);

      const validatedNickname = formValidationUtil.nickname(target.value);
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
          name: 'nickname',
          label: '닉네임',
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

Nickname.propTypes = {
  nickname: PropTypes.string.isRequired,
  dispatch: PropTypes.func.isRequired
};

export default React.memo(Nickname);
