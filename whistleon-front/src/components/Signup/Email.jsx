import React, { useState } from 'react';
import PropTypes from 'prop-types';
import InputUnderline from '../InputUnderline';
import EventButton from '../EventButton';

import validate from './FormValidation';
import { isError, isValidationError } from '@utils/error';

const Email = ({ email, dispatch }) => {
  const [validationMessage, setValidationMessage] = useState(null);

  const onInput = ({ target }) => {
    try {
      dispatch(target);

      const validatedEmail = validate.email(target.value);
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
          name: 'email',
          label: '이메일',
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

Email.propTypes = {
  email: PropTypes.string.isRequired,
  dispatch: PropTypes.func.isRequired
};

export default React.memo(Email);
