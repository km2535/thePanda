import ProductTrackerRankAPI from 'apis/response/datalab/productRandking';
import React, { useRef, useState } from 'react';
import { HiOutlineSearch } from 'react-icons/hi';
import { Product } from 'types/productTypes/productType,';
import parse from 'html-react-parser';


const RankingTrackerNaver = () => {
  const [naverSearch, setNaverSearch] = useState({ naverMid: '', naverKeyword: "" });
  const [data, setData] = useState<{ [key: string]: Product }>({}); 
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
          ProductTrackerRankAPI(keywords, mid).then((d)=>setData(d))
        } 
  }
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
             <div className='text-4xl font-extrabold mb-5'> 추적 상품</div>
             <div className={'flex'}>
               {Object.entries(data).map(([keyword, product]) => (
                 <>
                   {product.productId === "" ? <div>{keyword} 검색 결과는 200위가 넘어 추적 할 수 없습니다.</div> : 
                     <div className="bg-white shadow-md rounded-lg p-4 max-w-[600px]  mb-5 mr-5">
                      <div className="flex">
                        <div className="mr-4 max-w-[150px] w-4/12">
                          <img src={product.image} alt={product.keyword} className="w-full h-full object-cover rounded-md" />
                        </div>
                        <div className="flex flex-col justify-between w-6/12">
                          <div>
                            <div className="text-lg font-bold">
                              <a target='_blank' href={product.link} className="text-blue-500 hover:underline" rel="noreferrer">{parse(product.title)}</a>
                            </div>
                            <div className="text-gray-600">{product.lprice} 원</div>
                            <div className="text-gray-500">
                              {product.category1}
                              {product.category2 ? " > " + product.category2 : ""}
                              {product.category3 ? " > " + product.category3 : ""}
                              {product.category4 ? ` > ${product.category4}` : ""}
                            </div>
                          </div>
                          <div className="text-gray-500">
                            <div>브랜드: {product.brand}</div>
                            <div>판매몰: {product.mallName}</div>
                            <div>생산자: {product.maker}</div>
                          </div>
                        </div>
                         <div className={'flex flex-col w-2/12'}>
                          <div className="text-gray-500 font-bold text-xl text-right">{product.rank}위</div>
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

 export default RankingTrackerNaver;