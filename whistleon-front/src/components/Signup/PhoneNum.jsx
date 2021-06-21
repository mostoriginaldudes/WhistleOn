import React from 'react';
import PropTypes from 'prop-types';
import InputUnderline from '../InputUnderline';

const PhoneNum = ({ phoneNum, onInput }) => {
  return (
    <InputUnderline
      inputAttr={{
        readOnly: false,
        type: 'text',
        name: '연락처 ("-" 없이)',
        required: true,
        value: phoneNum,
        minLength: 10,
        maxLength: 11,
        onInput
      }}
    />
  );
};

PhoneNum.propTypes = {
  phoneNum: PropTypes.string.isRequired,
  onInput: PropTypes.func.isRequired
};

export default PhoneNum;
