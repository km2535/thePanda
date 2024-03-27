import KeywordDetailAPI from 'apis/response/keywordDetail';
import ProductListAPI from 'apis/response/productList';
import Product from 'component/keyword/product';
import { useEffect, useState } from 'react';
import {  useSearchParams } from 'react-router-dom';

export default function Analysis() {
  const [searchParams] = useSearchParams();
  const [productDetail, setProductDetail] = useState<any>();
  const [productList, setProductList] = useState<any>([]);
 useEffect(() => {
    const keyword = searchParams.get('keyword');
   if (keyword != null) { 
      // useState로 결과를 받아서 product 컴포넌트로 데이터를 넘겨준다.
     (KeywordDetailAPI(keyword, setProductDetail));
     ProductListAPI(keyword,setProductList);
    }
  }, [searchParams])
  return (
    <div>
      <Product productDetail={productDetail} productList={productList} />
    </div>
  );
}

