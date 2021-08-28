import httpRequest from './common';

// signup
export const sendCodeToEmail = (email) => httpRequest.get(`auth/user/email/${email}`);
export const checkSentCodeToEmail = (email, code) => httpRequest.get(`auth/email/${email}/code/${code}`);
export const checkIfNicknameExist = (nickname) => httpRequest.get(`users/nickname/${nickname}`);
export const submitSignupForm = (signupForm) => httpRequest.post('users', signupForm);

// login
export const submitLoginForm = (loginForm) => httpRequest.post('auth/login', loginForm);
