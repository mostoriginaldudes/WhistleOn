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
  eventHanlder: ({ target }) => console.log(target),
  text: 'click',
  color: 'yellow'
};

EventButton.propTypes = {
  eventHandler: PropTypes.func,
  text: PropTypes.string.isRequired,
  color: PropTypes.string.isRequired
};

export default EventButton;
