import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import Modal from '../Modal';
import InputUnderline from '../InputUnderline';
import EventButton from '../EventButton';
import validate from './Validate';
import { sendCodeToEmail, checkSentCodeToEmail } from '@apis';
import { convertFromMsToTime } from '@utils/date';
import ValidationError from '@/errors/ValidationError';
import AuthorizationError from '@/errors/AuthorizationError';

const Email = ({ email, dispatch }) => {
  const [validationMessage, setValidationMessage] = useState(null);
  const [onModal, setOnModal] = useState(false);
  const [codeSentToEmail, setCodeSentToEmail] = useState('');
  const [timeLimit, setTimeLimit] = useState(null);
  const [timeLeftText, setTimeLeftText] = useState(null);
  const [intervalId, setIntervalId] = useState(null);

  useEffect(() => {
    setTimeLimit(Date.now() + 1000 * 60 * 3);
  }, [timeLimit, setTimeLimit]);

  const onChange = ({ target }) => {
    try {
      dispatch(target);
      if (!validate.email(target.value)) {
        throw new ValidationError('올바르지 않은 이메일 형식입니다.');
      }
      setValidationMessage(null);
    } catch (error) {
      setValidationMessage(error.message);
      dispatch(error);
    }
  };

  const closeModal = () => {
    setOnModal(false);
    clearInterval(intervalId);
  };

  const sendCodeToCheck = async (email) => {
    try {
      const response = await sendCodeToEmail(email);
      if (response.statusText === 'OK') {
        setOnModal(true);

        if (!intervalId) {
          const id = setInterval(() => {
            const milsec = timeLimit - Date.now();

            if (milsec <= 0) {
              clearInterval(intervalId);
              setIntervalId(null);
              closeModal();
              return;
            }

            setTimeLeftText(convertFromMsToTime(milsec));
          }, 1000);
          setIntervalId(id);
        }
      } else {
        throw new AuthorizationError('올바르지 않은 이메일입니다.');
      }
    } catch (error) {
      console.error(error);
    }
  };

  // 인증 코드 검증
  const checkCodeSentToEmail = async (code) => {
    try {
      const response = await checkSentCodeToEmail(code);
      console.log(response);
      closeModal();
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="signup__info__input__wrapper">
      <InputUnderline
        inputAttr={{
          type: 'email',
          name: 'email',
          label: '이메일',
          required: true,
          value: email,
          onChange,
          onError: validationMessage
        }}
      />
      <EventButton
        text="인증 코드 전송"
        color="gray"
        eventHandler={(e) => {
          e.stopPropagation();
          sendCodeToCheck(email);
        }}
      />
      {onModal && (
        <Modal header="이메일 인증" closeModal={closeModal}>
          <InputUnderline
            inputAttr={{
              type: 'password',
              name: 'email-code',
              label: '이메일로 전송된 코드를 입력하세요.',
              required: true,
              value: codeSentToEmail,
              onChange: ({ target: { value } }) => setCodeSentToEmail(value)
            }}
          />
          <h3 className="modal__timer">{timeLeftText}</h3>
          <EventButton text="이메일 코드 인증" color="gray" eventHandler={() => checkCodeSentToEmail(codeSentToEmail)} />
        </Modal>
      )}
    </div>
  );
};

Email.propTypes = {
  email: PropTypes.string.isRequired,
  dispatch: PropTypes.func.isRequired
};

export default React.memo(Email);
