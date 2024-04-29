import { ProductListCoupangAPI } from 'apis/response/productList';
import React, { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';

const ProductListCoupang = () => {
  const [searchParams] = useSearchParams();
  const [productList, setProductList] = useState([]);

  useEffect(() => { 
    const keyword = searchParams.get("keyword")
    if (keyword) {
      ProductListCoupangAPI(keyword,setProductList);
    } 
  }, [searchParams]) 
     return(
       <>
         {productList.map((item, i) =>
            i <10 ?
             <div key={i} id={i + ""}>
             {item}
           </div> : "")}
       </>
    )
  } 

 export default ProductListCoupang;
