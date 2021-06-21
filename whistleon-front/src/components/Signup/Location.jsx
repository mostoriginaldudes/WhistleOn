import React from 'react';
import PropTypes from 'prop-types';
import InputUnderline from '../InputUnderline';

const Location = ({ location, onFocus }) => {
  return (
    <InputUnderline
      inputAttr={{
        type: 'text',
        name: '연고지',
        readOnly: true,
        required: true,
        value: location,
        onFocus
      }}
    />
  );
};

Location.propTypes = {
  location: PropTypes.string.isRequired,
  onFocus: PropTypes.func.isRequired
};

export default React.memo(Location);
