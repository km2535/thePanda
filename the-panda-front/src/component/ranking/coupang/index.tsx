import { ProductTrackerRankCoupangAPI } from 'apis/response/datalab/productRandking';
import React, { useRef, useState } from 'react';
import { HiOutlineSearch } from 'react-icons/hi';
import { CoupangProduct } from 'types/productTypes/productType,';

const RankingTrackerCoupang = () => {
  const [coupangSearch, setCoupangSearch] = useState({ selectOption: 'ranking', productId: '', coupangKeyword: "" });
  const [data, setData] = useState<{ [key: string]: CoupangProduct }>({}); 
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
        ProductTrackerRankCoupangAPI(keywords, productId, option).then((d) => setData(d));
    } 
  } 
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
             <div className='text-4xl font-extrabold mb-5'> 추적 상품</div>
             <div className={'flex'}>
               {Object.entries(data).map(([keyword, product]) => (
                 <>
                   {product.productId ==="" ? <div>{keyword} 검색 결과는 200위가 넘어 추적 할 수 없습니다.</div> : 
                     <div className="bg-white shadow-md rounded-lg p-4 max-w-[600px] mb-5 mr-5">
                      <div className="flex">
                        <div className="mr-4 max-w-[150px] w-4/12">
                           <img src={product.image } alt={product.productName} className="w-full h-full object-cover rounded-md" />
                        </div>
                        <div className="flex flex-col justify-between w-6/12">
                          <div>
                            <div className="text-lg font-bold">
                               <a target='_blank' href={product.link} className="text-blue-500 hover:underline" rel="noreferrer">{product.productName}</a>
                            </div>
                             <div className="text-gray-600">
                               <span className='line-through text-slate-500 text-sm mr-2'>{product.originalPrice}원</span>
                               <span className='text-rose-600 font-extrabold'>{product.salePrice}원</span>
                             </div>
                            <div className="text-gray-500">
                              
                            </div>
                          </div>
                          <div className="text-gray-500">
                             <div>배송 : {product.deliveryInfo}</div>
                             <div>혜택 : {product.rewqardInfo}</div>
                             <div className={'flex items-center'}>
                               <div className={'w-2/6'}>별점 : {product.rating} </div>
                               <div className={'w-2/6'}>
                                 {product.rocket} 상품
                               </div>
                               {product.rocketImgUrl !== "" ?
                               <div className='2/6'>
                                  <img className={'w-20'} src={product.rocketImgUrl} alt="" />
                               </div>
                               : ""}</div>
                          </div>
                         </div>
                         <div className={'flex flex-col w-2/12'}>
                          <div className="text-gray-500 font-bold text-xl text-right">{product.ranking}위</div>
                          <div className="text-gray-500 font-bold text-sm text-right">{keyword}</div>
                         </div>
                      </div>
                    </div>
                   } 
                 </>
                ))}
             </div>
            </section>
         </div>
       </>
    )
  } 

 export default RankingTrackerCoupang;