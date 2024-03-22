import React from 'react';
import { Outlet } from 'react-router-dom';

export default function Keyword() {
  // 여기서 데이터를 불러와서 필요한 데이터를 자식 컴포넌트로 보내줌
  return (
    <div>
        <Outlet/>
    </div>
  );
}

