import React from 'react';
import PropTypes from 'prop-types';
import DaumPostcode from 'react-daum-postcode';
import './post-code.scoped.scss';

const PostCode = ({ onAddress, setOnAddress }) => {
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

    console.log(fullAddress); // e.g. '서울 성동구 왕십리로2길 20 (성수동1가)'

    setOnAddress(false);
  };

  return <>{onAddress && <DaumPostcode onComplete={handleComplete} width={'100%'} height={'100%'} />}</>;
};

PostCode.propTypes = {
  onAddress: PropTypes.bool.isRequired,
  setOnAddress: PropTypes.func.isRequired,
  inputLocationToForm: PropTypes.func
};
export default PostCode;
