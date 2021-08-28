export default class AuthorizationError extends Error {
  constructor(message) {
    super(message);
    this.name = 'AuthorizationError';
  }
  toString() {
    return `${this.name}: ${this.message}`;
  }
}
