import ValidationError from '@/errors/ValidationError';

const isError = (arg) => arg instanceof Error;

const isValidationError = (arg) => arg instanceof ValidationError;

export { isError, isValidationError };
