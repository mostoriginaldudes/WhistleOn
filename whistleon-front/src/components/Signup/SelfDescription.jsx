import React, { useState } from 'react';
import PropTypes from 'prop-types';
import formValidationUtil from './Validate';
import { isError, isValidationError } from '@utils/error';

const SelfDescription = ({ description, dispatch }) => {
  const [validationMessage, setValidationMessage] = useState(null);

  const onInput = ({ target }) => {
    try {
      dispatch(target);

      const validatedDescription = formValidationUtil.description(target.value);
      if (isError(validatedDescription)) {
        throw validatedDescription;
      }

      setValidationMessage(null);
    } catch (error) {
      if (isValidationError(error)) {
        setValidationMessage(error.message);
      }
    }
  };

  return (
    <div style={{ width: '100%' }}>
      <h3 className="signup__info__input__guide">자기소개</h3>
      <textarea className="signup__info__input__description" name="description" onInput={onInput} value={description} />
      {validationMessage && <h6 className="signup__info__input__description__error">{validationMessage}</h6>}
    </div>
  );
};

SelfDescription.propTypes = {
  description: PropTypes.string.isRequired,
  dispatch: PropTypes.func.isRequired
};

export default React.memo(SelfDescription);
