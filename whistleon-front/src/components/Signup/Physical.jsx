import React from 'react';
import PropTypes from 'prop-types';
import InputUnderline from '../InputUnderline';

const Physical = ({ height, weight, onInputHeight, onInputWeight }) => {
  return (
    <div className="signup__info__input__wrapper">
      <InputUnderline
        inputAttr={{
          type: 'text',
          name: '키 (cm)',
          min: 100,
          max: 250,
          required: true,
          value: height,
          onInput: onInputHeight
        }}
      />
      <InputUnderline
        inputAttr={{
          type: 'text',
          name: '몸무게 (kg)',
          min: 30,
          max: 200,
          required: true,
          value: weight,
          onInput: onInputWeight
        }}
      />
    </div>
  );
};

Physical.propTypes = {
  height: PropTypes.string.isRequired,
  weight: PropTypes.string.isRequired,
  onInputHeight: PropTypes.func.isRequired,
  onInputWeight: PropTypes.func.isRequired
};

export default Physical;
