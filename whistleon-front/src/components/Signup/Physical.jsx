import React, { useState } from 'react';
import InputUnderline from '../InputUnderline';
import validate from './FormValidation';
import { isError } from '@utils/error';

const Physical = () => {
  const [height, setHeight] = useState('');
  const [weight, setWeight] = useState('');
  const [messageForHeight, setMessageForHeight] = useState(null);
  const [messageForWeight, setMessageForWeight] = useState(null);

  const onInputHeight = ({ target: { value } }) => {
    try {
      setHeight(value);

      const validatedHeight = validate.height(value);

      if (isError(validatedHeight)) throw validatedHeight;

      setMessageForHeight(null);
    } catch ({ message }) {
      setMessageForHeight(message);
    }
  };

  const onInputWeight = ({ target: { value } }) => {
    try {
      setWeight(value);

      const validatedWeight = validate.weight(value);

      if (isError(validatedWeight)) throw validatedWeight;

      setMessageForWeight(null);
    } catch ({ message }) {
      setMessageForWeight(message);
    }
  };

  return (
    <div className="signup__info__input__wrapper">
      <InputUnderline
        inputAttr={{
          type: 'number',
          name: '키 (cm)',
          min: 100,
          max: 250,
          required: true,
          value: height,
          onInput: onInputHeight,
          onError: messageForHeight
        }}
      />
      <InputUnderline
        inputAttr={{
          type: 'number',
          name: '몸무게 (kg)',
          min: 30,
          max: 200,
          required: true,
          value: weight,
          onInput: onInputWeight,
          onError: messageForWeight
        }}
      />
    </div>
  );
};

export default Physical;
