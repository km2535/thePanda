import GetUserInfo from "apis/request/auth";
import { createContext, useContext, useEffect, useState } from "react";
import { useCookies } from "react-cookie";
import { useLocation } from "react-router-dom";
import { UserInfo } from "types/userTypes/userType";

// AuthContext의 기본값 타입을 null 대신에 { userInfo: UserInfo | null; logOutHandler: () => void; } 으로 설정합니다.
const AuthContext = createContext<{ userInfo: UserInfo | null; logOutHandler: () => void; }>({ userInfo: null, logOutHandler: () => {} });
export const useAuth = () => useContext(AuthContext);

export const AuthProvider = ({ children }:any) => {
  const [userInfo, setUserInfo] = useState<UserInfo | null>(null);
  const [cookie] = useCookies(['acessToken']);
  const [cookieid] = useCookies(['userid']);
  const location = useLocation();

  useEffect(() => {
    if (location.state && cookie.acessToken === location.state.token) {
      GetUserInfo(location.state.userid)
        .then((user) => setUserInfo(user))
        .catch((error) => console.error('Error fetching user information:', error));
      } else if(cookieid.userid !== undefined) {
      GetUserInfo(cookieid.userid)
        .then((user) => setUserInfo(user)) 
        .catch((error) => console.error('Error fetching user information:', error));
      
    }
  }, [cookie, cookieid, location.state]);

  const logOutHandler = () => {
    document.cookie = 'acessToken=; expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/';
    document.cookie = 'userid=; expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/';
    window.location.reload();
  };

  return (
    // value에 초기화된 userInfo와 logOutHandler를 전달합니다.
    <AuthContext.Provider value={{ userInfo: userInfo, logOutHandler: logOutHandler }}>
      {children}
    </AuthContext.Provider>
  );
};
