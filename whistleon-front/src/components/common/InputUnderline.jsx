import React, { useState } from 'react';
import '@/style/common/input-underline.scss';

const InputUnderline = (props) => {
  const { type, required, name } = props;
  const [value, setValue] = useState('');

  return (
    <div className="input_underline">
      <div className="input_underline__wrapper">
        <input
          className="input_underline__input"
          type={type}
          required={required}
        />
        <span className="input_underline__highlight" />
        <span className="input_underline__bar" />
        <label className="input_underline__label">{name}</label>
      </div>
    </div>
  );
};

export default InputUnderline;
