import React from 'react';
import './App.css';
import { Outlet} from 'react-router-dom';
import NavBar from 'component/navbar';

function App() {
  return (
    <>
   <NavBar mainMenuList={{
     "키워드 찾기": {"발굴":"/keyword/search","분석":"keyword/analysis","연관키워드":"keyword/related"}
     , "키워드 최적화": {"키워드 최적화":"/keyword/optimization"}
     , "순위 추적": {"순위 추적":"/product/tracking"}
     , "시장 분석(쿠팡)": {"시장 분석":"/analysis-coupang/market"}
     , "상품 분석(쿠팡)": {"시장 분석":"/analysis-coupang/product"}
     , "키워드 셔플": {"키워드 셔플":"/keyword/shuffle"}
    }} />
    <Outlet/>
    </>
  );
}

export default App;