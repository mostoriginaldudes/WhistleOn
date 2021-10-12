import React, { FC } from 'react';
import styled from '@emotion/styled';

const color = 'red';
const DivContainer = styled.div`
  font-size: 15px;
  background-color: green;
  &:hover {
    color: ${color};
  }
`;

const App: FC = () => {
  return <DivContainer>App</DivContainer>;
};

export default App;
