export default function createRequestThunk(type, request) {
  const SUCCESS = `${type}_SUCCESS`;
  const FAILURE = `${type}_FAILURE`;

  return (params) => async (dispatch) => {
    dispatch({ type });
    try {
      const response = await request(params);
      dispatch({
        type: SUCCESS,
        payload: response.data
      });
    } catch (error) {
      console.error(error);
      dispatch({
        type: FAILURE,
        payload: error,
        error: true
      });
      throw error;
    }
  };
}
