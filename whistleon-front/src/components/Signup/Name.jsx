import React from 'react';
import PropTypes from 'prop-types';
import InputUnderline from '../InputUnderline';

const Name = ({ name, onInput }) => {
  return (
    <InputUnderline
      inputAttr={{
        type: 'text',
        name: '이름',
        required: true,
        value: name,
        onInput
      }}
    />
  );
};

Name.propTypes = {
  name: PropTypes.string.isRequired,
  onInput: PropTypes.func.isRequired
};

export default React.memo(Name);
