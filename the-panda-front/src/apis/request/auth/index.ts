import axios from 'axios';

const GetUserInfo = async (userId:string|undefined) => {
  try {
    const response = await axios.post(`${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/get-user`, {
      userId: userId
    }, {
  withCredentials: true
});
    return response.data; // 유저 정보 반환
  } catch (error) {
    console.error('요청 오류:', error);
    throw error;
  }
};

export default GetUserInfo;