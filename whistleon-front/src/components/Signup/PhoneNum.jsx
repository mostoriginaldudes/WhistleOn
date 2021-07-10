import React, { useState } from 'react';
import PropTypes from 'prop-types';
import InputUnderline from '../InputUnderline';
import validate from './FormValidation';
import { isError, isValidationError } from '@utils/error';

const PhoneNum = ({ phoneNum, dispatch }) => {
  const [validationMessage, setValidationMessage] = useState(null);

  const onInput = ({ target }) => {
    try {
      dispatch(target);

      const validatePhoneNum = validate.phoneNum(target.value);
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
        name: 'phoneNum',
        label: '연락처 ("-" 없이)',
        required: true,
        value: phoneNum,
        onInput,
        onError: validationMessage
      }}
    />
  );
};

PhoneNum.propTypes = {
  phoneNum: PropTypes.string.isRequired,
  dispatch: PropTypes.func.isRequired
};

export default React.memo(PhoneNum);
