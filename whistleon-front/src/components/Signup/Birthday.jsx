import React, { useState } from 'react';
import PropTypes from 'prop-types';
import InputUnderline from '../InputUnderline';
import { today } from '@utils/date';
import formValidationUtil from './Validate';
import { isError, isValidationError } from '@utils/error';

const Birthday = ({ birthday, dispatch }) => {
  const [validationMessage, setValidationMessage] = useState(null);

  const onInput = ({ target }) => {
    try {
      dispatch(target);

      const validatedBirthday = formValidationUtil.birthday(target.value);
      if (isError(validatedBirthday)) {
        throw validatedBirthday;
      }

      setValidationMessage(null);
    } catch (error) {
      if (isValidationError(error)) {
        setValidationMessage(error.message);
      }
    }
  };

  return (
    <InputUnderline
      inputAttr={{
        type: 'date',
        name: 'birthday',
        label: '생년월일',
        min: '1950-01-01',
        max: today(),
        required: true,
        value: birthday,
        onInput,
        onError: validationMessage
      }}
    />
  );
};

Birthday.propTypes = {
  birthday: PropTypes.string.isRequired,
  dispatch: PropTypes.func.isRequired
};

export default React.memo(Birthday);
