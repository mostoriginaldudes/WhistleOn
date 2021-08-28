import React, { useState } from 'react';
import PropTypes from 'prop-types';
import InputUnderline from '../InputUnderline';
import formValidationUtil from './Validate';
import { isError, isValidationError } from '@utils/error';

const Physical = ({ height, weight, dispatch }) => {
  const [validationMessageForHeight, setValidationMessageForHeight] = useState(null);
  const [validationMessageForWeight, setValidationMessageForWeight] = useState(null);

  const onInputHeight = ({ target }) => {
    try {
      dispatch(target);

      const validatedHeight = formValidationUtil.height(target.value);

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

  const onInputWeight = ({ target }) => {
    try {
      dispatch(target);

      const validatedWeight = formValidationUtil.weight(target.value);

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
          label: '키 (cm)',
          name: 'height',
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
          label: '몸무게 (kg)',
          name: 'weight',
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

Physical.propTypes = {
  height: PropTypes.string.isRequired,
  weight: PropTypes.string.isRequired,
  dispatch: PropTypes.func.isRequired
};

export default Physical;
