import React from 'react';
import EventButton from '../EventButton';

import { useHistory } from 'react-router-dom';

const Buttons = () => {
  const history = useHistory();

  return (
    <>
      <EventButton text="회원가입" color="yellow" />
      <EventButton text="취소" color="gray" eventHandler={() => history.replace('/')} />
    </>
  );
};

export default React.memo(Buttons);
