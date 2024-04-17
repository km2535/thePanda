import React, { useEffect } from 'react';

// 키워드 검색 결과 props를 받아서 props.keyword를 이용하여 데이터를 받아옴
const NaverTopKeyword = ( {keyword} : any) => {
  useEffect(() => {  
    if (keyword !== "") {
      console.log("카카오 "+keyword)
    }
  },[keyword])
     return(
      <></>
    )
  } 

 export default NaverTopKeyword;