import React from 'react';
import PropTypes from 'prop-types';
import InputUnderline from '../InputUnderline';

const Birthday = ({ birthday, onInput }) => {
  const today = () => {
    const date = new Date();
    return `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`;
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
        onInput
      }}
    />
  );
};

Birthday.propTypes = {
  birthday: PropTypes.string.isRequired,
  onInput: PropTypes.func.isRequired
};

export default Birthday;
