module.exports = {
  extends: [
    'eslint:recommended',
    'plugin:react/recommended',
    'plugin:react-hooks/recommended',
    'plugin:prettier/recommended',
    'prettier/flowtype',
    'prettier/react'
  ],
  parser: 'babel-eslint',
  parserOptions: {
    ecmaFeatures: {
      jsx: true
    }
  },
  plugins: ['react', 'prettier', 'react-hooks'],
  env: {
    browser: true,
    node: true
  },
  settings: {
    react: {
      version: 'detect'
    }
  }
};
