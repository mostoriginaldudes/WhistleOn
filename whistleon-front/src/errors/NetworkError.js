export default class NetworkError extends Error {
  static name = 'NetworkError';

  constructor(message) {
    super(message);
  }

  get name() {
    return NetworkError.name;
  }

  toString() {
    return `${this.name}: ${this.message}`;
  }
}
