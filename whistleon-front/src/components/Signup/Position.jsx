import React, { useState, useEffect } from 'react';
import InputUnderline from '../InputUnderline';
import validate from './FormValidation';
import { isError } from '@utils/error';

const soccerPositions = ['GK', 'RW', 'CF', 'ST', 'LW', 'CAM', 'CM', 'CDM', 'CB', 'RWB', 'RB', 'LWB', 'LB'];

const Position = () => {
  const [mainPosition, setMainPosition] = useState('');
  const [subPosition, setSubPosition] = useState('');
  const [message, setMessage] = useState(null);

  const validatePosition = () => {
    try {
      const validatedPosition = validate.position(mainPosition, subPosition, soccerPositions);
      if (isError(validatedPosition)) throw validatedPosition;

      setMessage(null);
    } catch ({ message }) {
      setMessage(message);
    }
  };

  useEffect(validatePosition, [mainPosition, subPosition]);

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
          name: '메인 포지션',
          required: true,
          value: mainPosition,
          list: 'main-position',
          onChange: ({ target: { value } }) => setMainPosition(value),
          onError: message
        }}
      />
      {datalist('main-position')}
      <InputUnderline
        inputAttr={{
          type: 'text',
          name: '서브 포지션',
          required: true,
          value: subPosition,
          list: 'sub-position',
          onChange: ({ target: { value } }) => setSubPosition(value)
        }}
      />
      {datalist('sub-position')}
    </div>
  );
};

export default React.memo(Position);
