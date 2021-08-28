import React from 'react';
import PropTypes from 'prop-types';
import './event-button.scoped.scss';

const EventButton = ({ type, text, color, eventHandler }) => {
  return (
    <button type={type} className={`event_button event_button--${color}`} onClick={eventHandler}>
      {text}
    </button>
  );
};

EventButton.defaultProps = {
  type: 'button',
  text: 'click',
  color: 'yellow'
};

EventButton.propTypes = {
  type: PropTypes.oneOf(['submit', 'button']),
  text: PropTypes.string.isRequired,
  color: PropTypes.string.isRequired,
  eventHandler: PropTypes.func
};

export default React.memo(EventButton);
