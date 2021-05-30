import React from 'react';
import { useHistory } from 'react-router-dom';
import '@/style/common/link-button.scoped.scss';
import PropTypes from 'prop-types';

const LinkButton = ({ buttonAttr }) => {
  const { btnDir, btnLinkTo, btnText } = buttonAttr;
  const history = useHistory();

  return (
    <button className="common__btn" onClick={() => history.push(btnLinkTo)}>
      {btnDir === 'left' && <p className="common__btn--left">←</p>}
      <div className={`common__text common__text--${btnDir === 'left' ? 'left' : 'right'}`}>
        <p>{btnText}</p>
      </div>
      {btnDir === 'right' && <p className="common__btn--right">→</p>}
    </button>
  );
};

LinkButton.propTypes = {
  buttonAttr: PropTypes.object.isRequired
};

export default LinkButton;
