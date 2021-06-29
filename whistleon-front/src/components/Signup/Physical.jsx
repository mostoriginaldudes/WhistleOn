import React, { useState } from 'react';
import InputUnderline from '../InputUnderline';
import validate from './FormValidation';
import { isError, isValidationError } from '@utils/error';

const Physical = () => {
  const [height, setHeight] = useState('');
  const [weight, setWeight] = useState('');
  const [validationMessageForHeight, setValidationMessageForHeight] = useState(null);
  const [validationMessageForWeight, setValidationMessageForWeight] = useState(null);

  const onInputHeight = ({ target: { value } }) => {
    try {
      setHeight(value);

      const validatedHeight = validate.height(value);

      if (isError(validatedHeight)) {
        throw validatedHeight;
      }

      setValidationMessageForHeight(null);
    } catch (error) {
      if (isValidationError(error)) {
        setValidationMessageForHeight(error.message);
      }
    }
  };

  const onInputWeight = ({ target: { value } }) => {
    try {
      setWeight(value);

      const validatedWeight = validate.weight(value);

      if (isError(validatedWeight)) {
        throw validatedWeight;
      }

      setValidationMessageForWeight(null);
    } catch (error) {
      if (isValidationError(error)) {
        setValidationMessageForWeight(error.message);
      }
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
          onError: validationMessageForHeight
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
          onError: validationMessageForWeight
        }}
      />
    </div>
  );
};

export default Physical;
