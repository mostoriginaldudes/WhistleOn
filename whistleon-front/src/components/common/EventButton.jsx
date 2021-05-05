import React from 'react';
import '@/style/common/event-button.scss';

const EventButton = (props) => {
  const { eventHandler, text, color } = props;

  return (
    <button
      className={`event_button event_button--${color}`}
      onClick={eventHandler}
    >
      {text}
    </button>
  );
};

EventButton.defaultProps = {
  eventHander: ({ target }) => console.log(target),
  text: 'click',
  color: 'yellow'
};

export default EventButton;
