import React, { useState } from 'react';
import validate from './FormValidation';
import { isError } from '@utils/error';

const SelfDescription = () => {
  const [description, setDescription] = useState('');
  const [message, setMessage] = useState(null);

  const onInput = ({ target: { value } }) => {
    try {
      setDescription(value);

      const validatedDescription = validate.description(value);
      if (isError(validatedDescription)) throw validatedDescription;

      setMessage(null);
    } catch ({ message }) {
      setMessage(message);
    }
  };

  return (
    <div style={{ width: '100%' }}>
      <h3 className="signup__info__input__guide">자기소개</h3>
      <textarea className="signup__info__input__description" onInput={onInput} value={description} />
      {message && <h6 className="signup__info__input__description__error">{message}</h6>}
    </div>
  );
};

export default React.memo(SelfDescription);
