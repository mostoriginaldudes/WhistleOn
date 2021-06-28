import React from 'react';
import PropTypes from 'prop-types';
import DaumPostcode from 'react-daum-postcode';
import { MdClose } from 'react-icons/md';
import './post-code.scoped.scss';

const PostCode = ({ onAddress, setOnAddress, setLocation }) => {
  const handleComplete = (data) => {
    let fullAddress = data.address;
    let extraAddress = '';

    if (data.addressType === 'R') {
      if (data.bname !== '') {
        extraAddress += data.bname;
      }
      if (data.buildingName !== '') {
        extraAddress += extraAddress !== '' ? `, ${data.buildingName}` : data.buildingName;
      }
      fullAddress += extraAddress !== '' ? ` (${extraAddress})` : '';
    }

    setLocation(fullAddress); // e.g. '서울 성동구 왕십리로2길 20 (성수동1가)'

    setOnAddress(false);
  };

  const closeAddress = () => setOnAddress(false);

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
  setLocation: PropTypes.func.isRequired
};
export default React.memo(PostCode);
