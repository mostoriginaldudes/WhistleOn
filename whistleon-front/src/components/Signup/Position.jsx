import React from 'react';
import PropTypes from 'prop-types';
import InputUnderline from '../InputUnderline';

const soccerPosition = ['GK', 'RW', 'CF', 'ST', 'LW', 'CAM', 'CM', 'CDM', 'CB', 'RWB', 'RB', 'LWB', 'LB'];

const Position = ({ mainPosition, onChangeMainPosition, subPosition, onChangeSubPosition }) => {
  return (
    <div className="signup__info__input__wrapper">
      <InputUnderline
        inputAttr={{
          type: 'text',
          name: '선호 포지션 1',
          required: true,
          value: mainPosition,
          list: 'main-position',
          onChange: onChangeMainPosition
        }}
      />
      <datalist id="main-position">
        {soccerPosition.map((pos, index) => (
          <option value={pos} key={index} />
        ))}
      </datalist>
      <InputUnderline
        inputAttr={{
          type: 'text',
          name: '선호 포지션 2',
          required: true,
          value: subPosition,
          list: 'sub-position',
          onChange: onChangeSubPosition
        }}
      />
      <datalist id="sub-position">
        {soccerPosition.map((pos, index) => (
          <option value={pos} key={index} />
        ))}
      </datalist>
    </div>
  );
};

Position.propTypes = {
  mainPosition: PropTypes.string.isRequired,
  subPosition: PropTypes.string.isRequired,
  onChangeMainPosition: PropTypes.func.isRequired,
  onChangeSubPosition: PropTypes.func.isRequired
};

export default Position;
