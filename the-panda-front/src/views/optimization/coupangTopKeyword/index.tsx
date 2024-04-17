import React, { useEffect } from 'react';

const CoupangTopKeyword = ({keyword}: any) => {
   useEffect(() => {  
    if (keyword !== "") {
      console.log("쿠팡 "+keyword)
    }
  },[keyword])
     return(
      <></>
    )
  } 

 export default CoupangTopKeyword;