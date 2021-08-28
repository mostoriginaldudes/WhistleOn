import axios from 'axios';
import store from '@/store';

const createAxiosInstance = () =>
  axios.create({
    baseURL: 'https://www.whistleon.com/',
    timeout: 5000
  });

const setInterceptor = () => {
  const instance = createAxiosInstance();

  instance.interceptors.request.use(
    (config) => {
      config.headers.Authorization = store.getState().authToken || null;
      return config;
    },
    (error) => Promise.reject(error)
  );

  instance.interceptors.response.use(
    (config) => config,
    (error) => Promise.reject(error)
  );
  return instance;
};

export default setInterceptor();
