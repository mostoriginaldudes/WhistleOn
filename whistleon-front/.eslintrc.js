module.exports = {
  rules: {
    "react/jsx-uses-react": "error",
    "react/jsx-uses-vars": "error",
  },
  plugins: [
    "react"
  ],
  parser: 'babel-eslint',
  parserOptions: {
    ecmaFeatures: {
        jsx: true
    }
  },
  env: {
    "browser": true,
    "node": true
  },
};

