const today = () => {
  const date = new Date();
  return `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`;
};

const isValidDate = (strDate) => isFinite(new Date(strDate));

export { today, isValidDate };
