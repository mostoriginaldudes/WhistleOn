import React from 'react';
import PropTypes from 'prop-types';
import './event-button.scoped.scss';

const EventButton = ({ text, color, eventHandler }) => {
  return (
    <button className={`event_button event_button--${color}`} onClick={eventHandler}>
      {text}
    </button>
  );
};

EventButton.defaultProps = {
  text: 'click',
  color: 'yellow'
};

EventButton.propTypes = {
  type: PropTypes.oneOf(['submit']),
  text: PropTypes.string.isRequired,
  color: PropTypes.string.isRequired,
  eventHandler: PropTypes.func
};

export default React.memo(EventButton);
