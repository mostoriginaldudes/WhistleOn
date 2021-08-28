import React, { useState } from 'react';
import PropTypes from 'prop-types';
import InputUnderline from '../InputUnderline';
import formValidationUtil from './Validate';
import { isError, isValidationError } from '@utils/error';

const soccerPositions = ['GK', 'RW', 'CF', 'ST', 'LW', 'CAM', 'CM', 'CDM', 'CB', 'RWB', 'RB', 'LWB', 'LB'];

const Position = ({ position1, position2, dispatch }) => {
  const [validationMessage, setValidationMessage] = useState(null);

  const onChange = ({ target }) => {
    dispatch(target);
    validatePosition();
  };

  const validatePosition = () => {
    try {
      const validatedPosition = formValidationUtil.position(position1, position2, soccerPositions);
      if (isError(validatedPosition)) {
        throw validatedPosition;
      }

      setValidationMessage(null);
    } catch (error) {
      if (isValidationError(error)) {
        setValidationMessage(error.message);
      }
    }
  };

  const datalist = (type) => (
    <datalist id={type}>
      {soccerPositions.map((pos, index) => (
        <option value={pos} key={index} />
      ))}
    </datalist>
  );

  return (
    <div className="signup__info__input__wrapper">
      <InputUnderline
        inputAttr={{
          type: 'text',
          name: 'position1',
          label: '메인 포지션',
          required: true,
          value: position1,
          list: 'main-position',
          onChange,
          onError: validationMessage
        }}
      />
      {datalist('main-position')}
      <InputUnderline
        inputAttr={{
          type: 'text',
          name: 'position2',
          label: '서브 포지션',
          required: true,
          value: position2,
          list: 'sub-position',
          onChange
        }}
      />
      {datalist('sub-position')}
    </div>
  );
};

Position.propTypes = {
  position1: PropTypes.oneOf([...soccerPositions, '']).isRequired,
  position2: PropTypes.oneOf([...soccerPositions, '']).isRequired,
  dispatch: PropTypes.func.isRequired
};

export default React.memo(Position);
