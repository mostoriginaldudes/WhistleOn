import { SIGNUP_FORM, CHECK_EMAIL, CHECK_CODE_EMAIL_SENT, CHECK_NICKNAME } from '../constants';
import { submitSignupForm, sendCodeToEmail, checkSentCodeToEmail, checkIfNicknameExist } from '@apis';
import createRequestThunk from '@utils/thunk';

export const submitForm = createRequestThunk(SIGNUP_FORM, submitSignupForm);
export const checkEmail = createRequestThunk(CHECK_EMAIL, sendCodeToEmail);
export const checkCodeSentToEmail = createRequestThunk(CHECK_CODE_EMAIL_SENT, checkSentCodeToEmail);
export const checkNickname = createRequestThunk(CHECK_NICKNAME, checkIfNicknameExist);
