import React, { useState } from 'react';
import InputUnderline from '../InputUnderline';
import { today } from '@utils/date';
import validate from './FormValidation';
import { isError, isValidationError } from '@utils/error';

const Birthday = () => {
  const [birthday, setBirthday] = useState('');
  const [validationMessage, setValidationMessage] = useState(null);

  const onInput = ({ target: { value } }) => {
    try {
      setBirthday(value);

      const validatedBirthday = validate.birthday(value);
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
        name: '생년월일',
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

export default React.memo(Birthday);
