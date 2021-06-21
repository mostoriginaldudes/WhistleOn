import React from 'react';
import PropTypes from 'prop-types';

const SelfDescription = ({ onInput }) => {
  return (
    <div style={{ width: '100%' }}>
      <h3 className="signup__info__input__guide">자기소개</h3>
      <textarea className="signup__info__input__description" onInput={onInput} />
    </div>
  );
};

SelfDescription.propTypes = {
  onInput: PropTypes.func.isRequired
};

export default SelfDescription;
