import React from 'react';
import { render } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import App from './src/App';

describe('root component rendering test', () => {
  it('renders successfully', () => {
    const { container } = render(<App />);
    expect(container).toHaveTextContent('App');
  });
});
