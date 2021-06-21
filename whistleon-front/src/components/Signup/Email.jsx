import React from 'react';
import PropTypes from 'prop-types';
import InputUnderline from '../InputUnderline';
import EventButton from '../EventButton';

const Email = ({ email, onInput }) => {
  const checkEmail = (email) => {
    console.log(email);
  };

  return (
    <div className="signup__info__input__wrapper">
      <InputUnderline
        inputAttr={{
          type: 'email',
          name: '이메일',
          required: true,
          value: email,
          onInput
        }}
      />
      <EventButton text="이메일 인증" color="gray" eventHandler={checkEmail} />
    </div>
  );
};

Email.propTypes = {
  email: PropTypes.string.isRequired,
  onInput: PropTypes.func.isRequired
};

export default React.memo(Email);
