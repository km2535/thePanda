import { userTracker } from 'apis/request/tracking';
import { ProductTrackerRankCoupangAPI } from 'apis/response/datalab/productRandking';
import { useAuth } from 'component/context/AuthContext';
import React, { useEffect, useRef, useState } from 'react';
import { HiOutlineSearch } from 'react-icons/hi';
import { CoupangProduct } from 'types/productTypes/productType,';
import { v4 as uuidv4 } from 'uuid';
import CoupangProductCard from './card';
import LoadingSpinner from 'views/common/LoadingSpinner';

const RankingTrackerCoupang = ({ trackingHistory, trackerResult, eventHandler }: any) => {
  const { userInfo } = useAuth();
  const [isLoading, setIsLoading] = useState(false);
  const [coupangSearch, setCoupangSearch] = useState({ selectOption: 'scoreDesc', productId: '', coupangKeyword: "" });
  const [data, setData] = useState<{ [key: string]: CoupangProduct }>({}); 
  const [dataInUser, setDataInUser] = useState<{ [key: string]: CoupangProduct }[]>([]); 
  const productIdRef = useRef<any>();
  const keywordRef = useRef<any>();
   const changeEventHandler = (e:any) => {
     setCoupangSearch((prev) => ({ ...prev, [e.target.id]: e.target.value }))
   
  }
  const enterDown = (e:any) => {
    if (e.keyCode === 13) {
      intputEventHandler(e);
    }
  }
  const intputEventHandler = (e: any) => {
      e.preventDefault();
      if (coupangSearch.productId === "" || isNaN(Number(coupangSearch.productId))) {
              setCoupangSearch((prev) => ({ ...prev, naverMid: "" }))
          productIdRef.current.value = "";
          productIdRef.current.focus();
        }
      if (coupangSearch.coupangKeyword === "") {
          keywordRef.current.focus();
        }
      if (coupangSearch.coupangKeyword !== "" && Number(coupangSearch.productId) > 0) {
          const arr: string[] = [];
          coupangSearch.coupangKeyword.split(",").forEach((item) => arr.push(item.trim()));
          const option: string = coupangSearch.selectOption;
          const keywords: string = arr.join(",").trim();
        const productId: string = coupangSearch.productId;
        setIsLoading(true);
        ProductTrackerRankCoupangAPI(keywords, productId, option).then((d) => setData(d)).finally(() => setIsLoading(false))
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
        if (item.searchSource === 'coupang') {
          setIsLoading(true);
    ProductTrackerRankCoupangAPI(item.searchKeyword, item.productId, item.searchType)
      .then((d) => setDataInUser((prev) => [...prev, d])).finally(()=>setIsLoading(false))
        }
      });
    }
  }, [trackerResult])
     return( 
       <>
        <div className='flex flex-col mt-10 mb-10'>
            <section className='flex flex-col mt-10 mb-10'>
             <div className='text-4xl font-extrabold mb-5'>쿠팡 상품 순위 추적</div>
             <div className='flex shadow-xl max-w-[800px] bg-slate-200 items-center rounded-full p-4'>
               <select name="selectOption" id="selectOption" onChange={changeEventHandler}>
                 <option value="scoreDesc">쿠팡 랭킹순</option>
                 <option value="salePriceAsc">낮은 가격순</option>
                 <option value="salePriceDesc">높은 가격순</option>
                 <option value="saleCountDesc">판매량순</option>
                 <option value="latestAsc">최신순</option>
               </select>
                <input ref={productIdRef} className='w-4/12 h-12 bg-slate-200 focus:bg-slate-50 transition-all'type="text" id='productId' placeholder='상품id를 입력하세요' onChange={changeEventHandler} onKeyDown={enterDown} required />
                <input ref={keywordRef} className='w-6/12 h-12 bg-slate-200 focus:bg-slate-50 transition-all' type="text" id='coupangKeyword' placeholder='검색 키워드를 입력하세요(콤마로 여러단어 검색 최대 5개)' onChange={changeEventHandler} onKeyDown={enterDown} required/>
                <HiOutlineSearch className={'w-2/12 cursor-pointer size-9 text-iconsColor'}onClick={intputEventHandler} onSubmit={intputEventHandler} />
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
                 Object.entries(data).map(([keyword, product]) => (
                 <>{
                   product.productName === "" ? <div className={"w-full pl-10 pr-10 mb-10"}>"{keyword}" 검색 결과는 200위가 넘어 추적 할 수 없습니다.</div> : 
                   <form className={"w-full pl-10 pr-10"} onSubmit={saveProductId} key={uuidv4()}>
                       <CoupangProductCard productDetail={product} btnName={"저장하기"} eventHandler={eventHandler} keyword={keyword} />
                        <input type="hidden" name="id" value={userInfo?.userId+product.productId+keyword} />
                       <input type="hidden" name="keyword" value={keyword}/>
                       <input type="hidden" name="productId" value={product.productId} />
                       <input type="hidden" name="searchSource" value={"coupang"} />
                       <input type="hidden" name="searchType" value={coupangSearch.selectOption} />
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
                   {
                     isLoading ?
                       <div className={"flex text-center justify-center"}>
                          <LoadingSpinner isLoading={isLoading} />
                       </div>
                   :
                     (dataInUser.length > 0 ? dataInUser.map((object) => Object.entries(object).map(([keyword, product], _i) => (
                     <>
                       {product.productName === "" ?
                         <div className={"flex max-w-[600px] justify-between flex-col p-10"}>
                           <div className={"w-full pl-10 pr-10 mb-10"}>"{keyword}" 검색 결과는 200위가 넘어 추적 할 수 없습니다.</div>
                           <div className="text-gray-500 font-bold text-sm text-right h-full flex items-end">
                             <button className='w-full cursor-pointer text-right hover:text-black' type='submit' onClick={(e) => {
                                const id = userInfo?.userId + product.productId + keyword
                               eventHandler(e,id)
                             }}>{"삭제"}</button>
                            </div>
                         </div>
                         :
                          <div className='flex-col pr-10 pl-10'>
                            <CoupangProductCard productDetail={product} btnName={"삭제"} eventHandler={eventHandler} keyword={keyword} />
                          </div>
                         
                       }
                     </>
                     ))) : "저장된 상품이 없습니다.")
                   
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

 export default RankingTrackerCoupang;