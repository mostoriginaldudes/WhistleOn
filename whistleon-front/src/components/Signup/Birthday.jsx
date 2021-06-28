import React, { useState } from 'react';
import InputUnderline from '../InputUnderline';
import { today } from '@utils/date';
import validate from './FormValidation';
import { isError } from '@utils/error';

const Birthday = () => {
  const [birthday, setBirthday] = useState('');
  const [message, setMessage] = useState(null);

  const onInput = ({ target: { value } }) => {
    try {
      setBirthday(value);
      const validatedBirthday = validate.birthday(birthday);

      if (isError(validatedBirthday)) throw validatedBirthday;

      setMessage(null);
    } catch ({ message }) {
      setMessage(message);
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
        onError: message
      }}
    />
  );
};

export default React.memo(Birthday);
