import { SIGNUP_FORM, CHECK_EMAIL, CHECK_NICKNAME } from '../constants';

const initialState = {
  signupForm: {
    email: '',
    birthday: '',
    sido: '',
    sigungu: '',
    roadAddress: '',
    zonecode: '',
    password: '',
    name: '',
    nickname: '',
    phoneNum: '',
    height: '',
    weight: '',
    position1: '',
    position2: '',
    description: ''
  },
  isCheckedEmail: false,
  isCheckedNickname: false
};

function userReducer(state = initialState, action) {
  switch (action.type) {
    case CHECK_EMAIL:
      return {
        ...state,
        isCheckedEmail: action.payload
      };
    case CHECK_NICKNAME:
      return {
        ...state,
        isCheckedNickname: action.payload
      };
    case SIGNUP_FORM:
      return {
        ...state,
        signupForm: action.payload
      };
    default:
      return state;
  }
}

export default userReducer;
