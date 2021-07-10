import React, { useState } from 'react';
import PropTypes from 'prop-types';
import InputUnderline from '../InputUnderline';
import validate from './FormValidation';
import { isError, isValidationError } from '@utils/error';

const Location = ({ location, onFocus }) => {
  const [validationMessage, setValidationMessage] = useState(null);

  const onChange = ({ target: { value } }) => {
    try {
      const validatedLocation = validate.location(value);
      if (isError(validatedLocation)) {
        throw validatedLocation;
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
        type: 'text',
        name: 'roadAddress',
        label: '연고지',
        required: true,
        value: location,
        onFocus,
        onChange,
        onError: validationMessage
      }}
    />
  );
};

Location.propTypes = {
  location: PropTypes.string.isRequired,
  onFocus: PropTypes.func.isRequired
};

export default React.memo(Location);
