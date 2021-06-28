import ValidationError from './ValidationError';
import { today, isValidDate } from '@utils/date';

const validate = () => ({
  range(minValue, valueToValidate, maxValue) {
    return minValue <= valueToValidate && valueToValidate <= maxValue;
  },

  email(email) {
    return /[\w.]@[\w.]+\.\w+/gi.test(email) || new ValidationError('올바르지 않은 이메일 형식입니다.');
  },

  name(name) {
    return /^[가-힣]{2,}/g.test(name) || new ValidationError('한글 2자 이상 작성해주세요.');
  },

  nickname(nickname) {
    return (/^[가-힣a-z]+$/gi.test(nickname) && this.range(2, nickname, 20)) || new ValidationError('한글 또는 영문 최소 2글자 최대 20자를 입력해주세요.');
  },

  phoneNum(phoneNum) {
    return /010\d{7}$/g.test(phoneNum) || new ValidationError('연락처가 올바르지 않은 형식입니다.');
  },

  password(password) {
    const includesSpecificSymbols = () => /(!|@|#|\$)/g.test(password);
    const includesUpperCase = () => /[A-Z]/g.test(password);
    const includesLowerCase = () => /[a-z]/g.test(password);
    const includesRightLength = () => this.range(8, password.length, 20);

    return (
      [includesSpecificSymbols, includesUpperCase, includesLowerCase, includesRightLength].every((isValid) => isValid()) ||
      new ValidationError('특수문자(!@#$), 영문 대소문자, 숫자를 표함하는 8자리 이상 20자리 이하의 비밀번호를 작성하세요.')
    );
  },

  passwordCheck(password, passwordCheck) {
    return password === passwordCheck || new ValidationError('비밀번호가 일치하지 않습니다.');
  },

  birthday(birthday) {
    return (this.range('1950-01-01', birthday, today()) && isValidDate(birthday)) || new ValidationError('생년월일이 올바르지 않은 형식입니다.');
  },

  location(location) {
    return (!!location && location.length > 0) || new ValidationError('연고지를 입력해주세요.');
  },

  height(height) {
    return this.range(100, height, 250) || new ValidationError('신장(cm)이 올바르지 않습니다.');
  },

  weight(weight) {
    return this.range(30, weight, 200) || new ValidationError('체중(kg)이 올바르지 않습니다.');
  },

  position(main, sub, allPositions) {
    return (main !== sub && allPositions.includes(main) && allPositions.includes(sub)) || new ValidationError('포지션이 올바르지 않거나, 메인 포지션과 서브 포지션이 같습니다.');
  },

  description(description) {
    const descriptionLengthWithoutSpace = description.trim().length;
    return this.range(10, descriptionLengthWithoutSpace, 100) || new ValidationError('자기소개를 10자 이상 100자 이하 작성해주세요.');
  }
});

export default Object.freeze(validate());
