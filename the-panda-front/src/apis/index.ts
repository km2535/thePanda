const API_DOMAIN =`${ process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1`;
export const SNS_SIGN_IN_URL = (type: 'kakao' | 'naver' | 'google') => `${API_DOMAIN}/auth/oauth2/${type}`; 
