import React, { useState } from 'react';
import InputUnderline from '../InputUnderline';
import validate from './FormValidation';
import { isError } from '@utils/error';

const Name = () => {
  const [name, setName] = useState('');
  const [message, setMessage] = useState(null);

  const onInput = ({ target: { value } }) => {
    try {
      setName(value);

      const validatedName = validate.name(name);
      if (isError(validatedName)) throw validatedName;

      setMessage(null);
    } catch ({ message }) {
      setMessage(message);
    }
  };

  return (
    <InputUnderline
      inputAttr={{
        type: 'text',
        name: '이름',
        required: true,
        value: name,
        onInput,
        onError: message
      }}
    />
  );
};

export default React.memo(Name);
