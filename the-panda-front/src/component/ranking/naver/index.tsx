import ProductTrackerRankAPI from 'apis/response/datalab/productRandking';
import React, { useEffect, useRef, useState } from 'react';
import { HiOutlineSearch } from 'react-icons/hi';
import { Product } from 'types/productTypes/productType,';
import { useAuth } from 'component/context/AuthContext';
import { v4 as uuidv4 } from 'uuid';
import { userTracker } from 'apis/request/tracking';
import NavaerProductCart from './card';
import LoadingSpinner from 'views/common/LoadingSpinner';

const RankingTrackerNaver = ({trackingHistory,trackerResult,eventHandler}:any) => {
  const { userInfo } = useAuth();
  const [isLoading, setIsLoading] = useState(false);
  const [naverSearch, setNaverSearch] = useState({ naverMid: '', naverKeyword: "" });
  const [data, setData] = useState<{ [key: string]: Product }>({}); 
  const [dataInUser, setDataInUser] = useState<{ [key: string]: Product }[]>([]); 
  const midRef = useRef<any>();
  const keywordRef = useRef<any>();
  const changeEventHandler = (e:any) => {
    setNaverSearch((prev)=>({...prev, [e.target.id]:e.target.value}))
  }
  const enterDown = (e:any) => {
    if (e.keyCode === 13) {
      intputEventHandler(e);
    }
  }
  const intputEventHandler = (e: any) => {
    e.preventDefault();
      if (naverSearch.naverMid === "" || isNaN(Number(naverSearch.naverMid))) {
          setNaverSearch((prev) => ({ ...prev, naverMid: "" }))
          midRef.current.value = "";
          midRef.current.focus();
        }
      if (naverSearch.naverKeyword === "") {
          keywordRef.current.focus();
        }
      if (naverSearch.naverKeyword !== "" && Number(naverSearch.naverMid) > 0) {
          //정상 동작 ,
          const arr: string[] = [];
          naverSearch.naverKeyword.split(",").forEach((item) => arr.push(item.trim()));
          const keywords: string = arr.join(",").trim();
        const mid: string = naverSearch.naverMid;
        setIsLoading(true);
          ProductTrackerRankAPI(keywords, mid).then((d)=>setData(d)).finally(() => setIsLoading(false))
        } 
  }

  const saveProductId = (e: any) => {
    e.preventDefault();
    // userId로 검색을 해서 저장횟수를 가져와 해당 user의 저장횟수와 비교 후
    // 저장 함수 실행 여부 확인
    trackingHistory(e);
  }
  useEffect(() => {
    setDataInUser([])
    if (trackerResult !== undefined && trackerResult.length > 0) {
      trackerResult?.forEach((item: userTracker) => {
        if (item.searchSource === 'naver') {
    setIsLoading(true);
    ProductTrackerRankAPI(item.searchKeyword, item.productId)
      .then((d) => setDataInUser((prev) => [...prev, d])).finally(() => setIsLoading(false));
        }
      });
    }
  }, [trackerResult])

     return(
       <>
          <div className='flex flex-col mt-10 mb-10'>
            <section className='flex flex-col mt-10 mb-10'>
             <div className='text-4xl font-extrabold mb-5'>네이버 상품 순위 추적</div>
             <div className='flex shadow-xl max-w-[800px] bg-slate-200 items-center rounded-full p-4'>
                <input className='w-4/12 h-12 bg-slate-200 focus:bg-slate-50 transition-all' ref={midRef} type="text" id='naverMid' placeholder='Mid를 입력하세요' onChange={changeEventHandler} onKeyDown={enterDown} required />
                <input className='w-6/12 h-12 bg-slate-200 focus:bg-slate-50 transition-all' ref={keywordRef} type="text" id='naverKeyword' placeholder='검색 키워드를 입력하세요(콤마로 여러단어 검색 최대 5개)' onChange={changeEventHandler} onKeyDown={enterDown} required/>
                <HiOutlineSearch className={'w-2/12 cursor-pointer size-9 text-iconsColor'} onClick={intputEventHandler} onSubmit={intputEventHandler}/>
             </div>
            </section>
            <section className='flex flex-col mt-10 mb-10'>
             <div className='text-4xl font-extrabold mb-5'> 검색 상품</div>
             <div className={'flex flex-col'}>
               {
                  isLoading ?
                   <div className={"flex text-center justify-center"}>
                     <LoadingSpinner isLoading={isLoading} />
                    </div>
                   :
                 Object.entries(data).map(([keyword, product], i) => (
                 <>
                   {product.title === "" ?
                     <div className={"w-full pl-10 pr-10"} key={uuidv4()}>"{keyword}" 검색 결과는 200위가 넘어 추적 할 수 없습니다.</div> : 
                     <form onSubmit={saveProductId} key={uuidv4()} className={"w-full pl-10 pr-10"}>
                       <NavaerProductCart productDetail={product} btnName={"저장하기"} eventHandler={eventHandler} />
                       <input type="hidden" name="id" value={userInfo?.userId+product.productId+product.keyword} />
                       <input type="hidden" name="keyword" value={keyword}/>
                       <input type="hidden" name="productId" value={product.productId} />
                       <input type="hidden" name="searchSource" value={"naver"} />
                   </form>
                   } 
                 </>
                 ))
               }
             </div>
           </section>
           <section>
             {
               userInfo !== null ? 
                 <>
                   <div className='text-4xl font-extrabold mb-5'> 추적 상품</div>
                   {isLoading ?
                       <div className={"flex text-center justify-center"}>
                          <LoadingSpinner isLoading={isLoading} />
                       </div>
                   :
                     dataInUser.length > 0 ? dataInUser.map((object) => Object.entries(object).map(([keyword, product], i) => (
                     <>
                       {product.title === "" ?
                         <div className={"flex max-w-[600px] justify-between"}>
                           <div className={"mb-10"}>"{keyword}" 검색 결과는 200위가 넘어 추적 할 수 없습니다.</div>
                           <div className="text-gray-500 font-bold text-sm text-right h-full flex items-end">
                             <button className='w-full cursor-pointer text-right hover:text-black' type='submit' onClick={(e) => {
                                const id = userInfo?.userId + product.productId + keyword
                               eventHandler(e,id)
                             }}>{"삭제"}</button>
                            </div>
                         </div>
                         :
                         <div className='flex-col pr-10 pl-10'>
                            <NavaerProductCart productDetail={product} btnName={"삭제"} eventHandler={eventHandler} />
                         </div>
                       }
                     </>
                     ))) : "저장된 상품이 없습니다."
                   }
                 </>
               : 
                <></>
             }
           </section>
         </div>
       </>
    )
  } 

 export default RankingTrackerNaver;