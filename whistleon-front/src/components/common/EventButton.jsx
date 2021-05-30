import React from 'react';
import '@/style/common/event-button.scoped.scss';
import PropTypes from 'prop-types';

const EventButton = (props) => {
  const { eventHandler, text, color } = props;

  return (
    <button className={`event_button event_button--${color}`} onClick={eventHandler}>
      {text}
    </button>
  );
};

EventButton.defaultProps = {
  eventHander: ({ target }) => console.log(target),
  text: 'click',
  color: 'yellow'
};

EventButton.propTypes = {
  eventHandler: PropTypes.func.isRequired,
  text: PropTypes.string.isRequired,
  color: PropTypes.string.isRequired
};

export default EventButton;
