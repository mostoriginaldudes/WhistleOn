import React, { useState } from 'react';
import PropTypes from 'prop-types';
import InputUnderline from '../InputUnderline';
import validate from './FormValidation';
import { isError, isValidationError } from '@utils/error';

const Name = ({ name, dispatch }) => {
  const [validationMessage, setValidationMessage] = useState(null);

  const onInput = ({ target }) => {
    try {
      dispatch(target);

      const validatedName = validate.name(target.value);
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
        name: 'name',
        label: '이름',
        required: true,
        value: name,
        onInput,
        onError: validationMessage
      }}
    />
  );
};

Name.propTypes = {
  name: PropTypes.string.isRequired,
  dispatch: PropTypes.func.isRequired
};

export default React.memo(Name);
