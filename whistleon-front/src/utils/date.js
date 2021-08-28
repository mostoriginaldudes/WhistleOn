const today = () => {
  const date = new Date();
  return `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`;
};

const isValidDate = (strDate) => isFinite(new Date(strDate));

const convertFromMsToTime = (ms) => {
  const msToSecs = ms / 1000;
  const mins = Math.floor(msToSecs / 60);
  const secs = Math.floor(msToSecs % 60);
  return `${mins > 0 ? `${mins}분 ` : ' '}${secs}초 남았습니다.`;
};

export { today, isValidDate, convertFromMsToTime };
