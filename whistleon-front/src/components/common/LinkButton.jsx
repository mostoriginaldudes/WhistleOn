import React from 'react';
import '@/style/common/link-button.scss';

const LinkButton = ({dir, to, text }) => {
  return (
    <button className="common__btn" onClick={() => history.push(to)}>
      {dir === 'left' ? <p class="common__btn__left">←</p> : ''}
      <p 
        className={
          dir === 'left' 
            ? 'border-left' 
            : 'border-right'
        }
      >
        {text}
      </p>
      {dir === 'right' ? <p class="common__btn__right">→</p>: ''}
    </button>
  );
};

LinkButton.defaultProps = {
  dir: 'right',
  to: '/',
  text: ''
};

export default LinkButton;