import React from 'react';
import PropTypes from 'prop-types';
import './input-underline.scoped.scss';

const InputUnderline = ({ inputAttr }) => {
  const { type, required, onInput, onFocus, onBlur, onChange, value, name, readOnly, onError, ...args } = inputAttr;

  return (
    <div className="input_underline">
      <div className="input_underline__wrapper">
        <input
          className="input_underline__input"
          type={type}
          required={required}
          onInput={onInput}
          onFocus={onFocus}
          onBlur={onBlur}
          onChange={onChange}
          value={value}
          readOnly={readOnly}
          min={args.min}
          max={args.max}
          minLength={args.minLength}
          maxLength={args.maxLength}
          list={args.list}
        />
        <span className="input_underline__highlight" />
        <span className="input_underline__bar" />
        <label className="input_underline__label">{name}</label>
        {onError && <h6 className="input_underline__error">{onError}</h6>}
      </div>
    </div>
  );
};

InputUnderline.propTypes = {
  inputAttr: PropTypes.object.isRequired
};

export default React.memo(InputUnderline);
