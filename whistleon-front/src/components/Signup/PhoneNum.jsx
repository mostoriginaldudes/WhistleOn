import React, { useState } from 'react';
import InputUnderline from '../InputUnderline';
import validate from './FormValidation';
import { isError, isValidationError } from '@utils/error';

const PhoneNum = () => {
  const [phoneNum, setPhoneNum] = useState('');
  const [validationMessage, setValidationMessage] = useState(null);

  const onInput = ({ target: { value } }) => {
    try {
      setPhoneNum(value);

      const validatePhoneNum = validate.phoneNum(value);
      if (isError(validatePhoneNum)) {
        throw validatePhoneNum;
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
        readOnly: false,
        type: 'number',
        name: '연락처 ("-" 없이)',
        required: true,
        value: phoneNum,
        onInput,
        onError: validationMessage
      }}
    />
  );
};

export default React.memo(PhoneNum);
