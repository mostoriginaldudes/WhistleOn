import React from 'react';
import PropTypes from 'prop-types';
import DaumPostcode from 'react-daum-postcode';
import { MdClose } from 'react-icons/md';
import './post-code.scoped.scss';

const PostCode = ({ onAddress, setOnAddress, setLocation, dispatch }) => {
  const closeAddress = () => setOnAddress(false);

  const dispatchAllLocationInfo = (locationData) => {
    ['sido', 'sigungu', 'zonecode'].forEach((name) => {
      dispatch({ name, value: locationData[name] });
    });
  };

  const handleComplete = (locationData) => {
    dispatchAllLocationInfo(locationData);
    setLocation(locationData.roadAddress);
    closeAddress();
  };

  return (
    onAddress && (
      <>
        <div className="post-code" onClick={closeAddress}>
          <section className="post-code__mask" />
          <section className="post-code__popup">
            <header className="post-code__popup__header">
              <h3 className="post-code__popup__header__title">주소 검색</h3>
              <MdClose className="post-code__popup__header__btn" onClick={closeAddress} />
            </header>
            <article className="post-code__popup__body">
              <DaumPostcode onComplete={handleComplete} height="100%" />
            </article>
          </section>
        </div>
      </>
    )
  );
};

PostCode.propTypes = {
  onAddress: PropTypes.bool.isRequired,
  setOnAddress: PropTypes.func.isRequired,
  setLocation: PropTypes.func.isRequired,
  dispatch: PropTypes.func.isRequired
};
export default React.memo(PostCode);
