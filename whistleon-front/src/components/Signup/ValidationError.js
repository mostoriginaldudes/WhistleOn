export default class ValidationError extends Error {
  constructor(message) {
    super(message);
    this.name = 'ValidationError';
  }
  toString() {
    return `${this.name}: ${this.message}`;
  }
}
