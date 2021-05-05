import React from 'react';
import '@/style/common/link-button.scss';

const LinkButton = ({ dir, to, text }) => {
  return (
    <button className="common__btn" onClick={() => history.push(to)}>
      {dir === 'left' && <p className="common__btn--left">←</p>}
      <div
        className={`common__text common__text--${
          dir === 'left' ? 'left' : 'right'
        }`}
      >
        <p>{text}</p>
      </div>
      {dir === 'right' && <p className="common__btn--right">→</p>}
    </button>
  );
};

LinkButton.defaultProps = {
  dir: 'right',
  to: '/',
  text: ''
};

export default LinkButton;
