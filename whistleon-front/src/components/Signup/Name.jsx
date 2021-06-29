import React, { useState } from 'react';
import InputUnderline from '../InputUnderline';
import validate from './FormValidation';
import { isError, isValidationError } from '@utils/error';

const Name = () => {
  const [name, setName] = useState('');
  const [validationMessage, setValidationMessage] = useState(null);

  const onInput = ({ target: { value } }) => {
    try {
      setName(value);

      const validatedName = validate.name(name);
      if (isError(validatedName)) throw validatedName;

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
        type: 'text',
        name: '이름',
        required: true,
        value: name,
        onInput,
        onError: validationMessage
      }}
    />
  );
};

export default React.memo(Name);
