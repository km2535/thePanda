import KeywordDetailAPI from 'apis/response/keywordDetail';
import ProductListAPI, { ProdcutExceptAbroadAPI } from 'apis/response/productList';
import Product from 'component/keyword/product';
import { useEffect, useRef, useState } from 'react';
import { HiOutlineSearch } from 'react-icons/hi';
import {  useNavigate, useSearchParams } from 'react-router-dom';

export default function Analysis() {
  const [searchParams] = useSearchParams();
  const [productDetail, setProductDetail] = useState<any>();
  const [productList, setProductList] = useState<any>([]);
  const [searchText, setSearchText] = useState<string>("");
  const [productCountExceptAbroad, setProductCountExceptAbroad] = useState<number>(0);
  const inputRef = useRef<any>();
  const navigate = useNavigate();
 useEffect(() => {
   const keyword = searchParams.get('keyword');
   if (keyword != null) { 
      // useState로 결과를 받아서 product 컴포넌트로 데이터를 넘겨준다.
     (KeywordDetailAPI(keyword, setProductDetail));
     ProductListAPI(keyword, setProductList);
     ProdcutExceptAbroadAPI(keyword, setProductCountExceptAbroad);
    }
 }, [searchParams])
 
  const changeHandler = (e:any) => {
    setSearchText(e.target.value);
  }

  const submitHandler = () => {
    navigate(`/keyword/analysis?keyword=${searchText}`)
    inputRef.current.value = "";
    setSearchText("");
  }
  const enterDwon = (e:any) => {
     if (e.keyCode === 13) {
      submitHandler();
    }
  }
  return (
    <div >
      <div className="flex justify-center items-center">
        <div className={"relative w-3/5 mt-10 mb-10"}>
          <input type="text" placeholder="검색" className="w-full border rounded-full py-2 px-4 pr-10 focus:outline-none focus:ring-2 focus:ring-gray-400 focus:border-transparent transition duration-300 ease-in-out focus:placeholder:invisible" onChange={changeHandler} onKeyDown={enterDwon} ref={inputRef}/>
          <div className="absolute inset-y-0 right-0 pr-3 flex items-center">
            <HiOutlineSearch className={'size-5 text-iconsColor cursor-pointer'} onClick={submitHandler}/>
          </div>
        </div>
      </div>
      <Product productDetail={productDetail} productList={productList} productCountExceptAbroad={productCountExceptAbroad} />
    </div>
  );
}

