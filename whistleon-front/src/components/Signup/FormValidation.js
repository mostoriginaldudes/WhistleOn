class FormValidation {
  validateEmail(email) {
    return /[\w.]+@[\w.]+\.\w+/gi.test(email);
  }
  validateName(name) {
    return /^[가-힣]{2,}/g.test(name);
  }
  validatePassword(password) {
    // eslint-disable-next-line prettier/prettier
    return [
      () => /(!|@|#|\$)/g.test(password), 
      () => /[A-Z]/g.test(password), 
      () => /[a-z]/g.test(password), 
      () => 8 <= password.length && password.length <= 20
    ].every((isValid) => isValid());
  }
  validatePasswordCheck(password, passwordCheck) {
    return password === passwordCheck;
  }
  validatePhoneNum(phoneNum) {
    return /010-\d{4}-\d{4}/g.test(phoneNum);
  }
  validateDescription(description) {
    const descriptionLengthWithoutSpace = description.trim().length;
    return 10 <= descriptionLengthWithoutSpace && descriptionLengthWithoutSpace <= 100;
  }
}

export default new FormValidation();
