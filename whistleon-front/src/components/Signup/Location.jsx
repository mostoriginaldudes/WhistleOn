import React, { useState } from 'react';
import PropTypes from 'prop-types';
import InputUnderline from '../InputUnderline';
import validate from './FormValidation';
import { isError } from '@utils/error';

const Location = ({ location, onFocus }) => {
  const [message, setMessage] = useState(null);
  const onChange = ({ target: { value } }) => {
    try {
      const validatedLocation = validate.location(value);
      if (isError(validatedLocation)) throw validatedLocation;

      setMessage(null);
    } catch ({ message }) {
      setMessage(message);
    }
  };
  return (
    <InputUnderline
      inputAttr={{
        type: 'text',
        name: '연고지',
        required: true,
        value: location,
        onFocus,
        onChange,
        onError: message
      }}
    />
  );
};

Location.propTypes = {
  location: PropTypes.string.isRequired,
  onFocus: PropTypes.func.isRequired
};

export default React.memo(Location);
