import NavBar from 'component/navbar';
import React from 'react';

export default function Landing() {
  return (
    <div>
      <NavBar mainMenuList={{
        "키워드분석": "/keyword/analysis"
        , "키워드검색": "/keyword/search"
        , "상품 분석": "/product/analysis"
        , "상품 순위": "/product/ranking"
        , "상품 소싱": "/product/sourcing"
      }} />
    </div>
  );
}

