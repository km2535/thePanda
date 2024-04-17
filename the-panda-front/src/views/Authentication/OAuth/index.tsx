
import React, { useEffect } from 'react';
import { useCookies } from 'react-cookie';
import { useNavigate, useParams } from 'react-router-dom';

export default function OAuth() {
  const { token, expirationTime, userid } = useParams();
  const [cookie, setCookie] = useCookies();
  const navigate = useNavigate();

  useEffect(() => {
    if (!token || !expirationTime) {
      return;
    }
    const now = (new Date().getTime());
    const expires = new Date(now + (Number(expirationTime) * 4000));
    setCookie('acessToken', token, { expires, path: '/' });
    setCookie('userid', userid, { expires, path: '/' });
    // 토큰을 저장하는 api. 
    console.log(now, expires)
    
    navigate('/', { state: { token, userid } })
    window.location.reload();
    
  }, [expirationTime, navigate, setCookie, token, userid])

  return (
    <div>
      
    </div>
  );
}
