import React, { useEffect } from 'react';

const KakaoTopKeyword = ({keyword} : any) => {
   useEffect(() => {  
    if (keyword !== "") {
      console.log("네이버 "+keyword)
    }
  },[keyword])
     return(
      <></>
    )
  } 

 export default KakaoTopKeyword;