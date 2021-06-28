import React, { useState } from 'react';
import InputUnderline from '../InputUnderline';
import validate from './FormValidation';
import { isError } from '@utils/error';

const PhoneNum = () => {
  const [phoneNum, setPhoneNum] = useState('');
  const [message, setMessage] = useState(null);

  const onInput = ({ target: { value } }) => {
    try {
      setPhoneNum(value);

      const validatePhoneNum = validate.phoneNum(phoneNum);
      if (isError(validatePhoneNum)) throw validatePhoneNum;

      setMessage(null);
    } catch ({ message }) {
      setMessage(message);
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
        onError: message
      }}
    />
  );
};

export default React.memo(PhoneNum);
