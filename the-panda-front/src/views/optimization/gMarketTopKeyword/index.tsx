import React, { useEffect } from 'react';

const GmarketTopKeyword = ({keyword}: any) => {
   useEffect(() => {  
    if (keyword !== "") {
      console.log("지마켓 "+keyword)
    }
  },[keyword])
     return(
      <></>
    )
  } 

 export default GmarketTopKeyword;