import React from 'react';
import PropTypes from 'prop-types';
import InputUnderline from '../InputUnderline';
import EventButton from '../EventButton';

const Nickname = ({ nickname, onInput }) => {
  return (
    <div className="signup__info__input__wrapper">
      <InputUnderline
        inputAttr={{
          type: 'text',
          name: '닉네임',
          required: true,
          value: nickname,
          onInput
        }}
      />
      <EventButton text="중복 확인" color="gray" />
    </div>
  );
};

Nickname.propTypes = {
  nickname: PropTypes.string.isRequired,
  onInput: PropTypes.func.isRequired
};

export default React.memo(Nickname);
