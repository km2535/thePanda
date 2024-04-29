import { getGmarketProductData } from 'apis/request/analysis/market';
import SearchInputBasic from 'component/searchInput/input';
import React, { useRef, useState } from 'react';
import { GmarketProducts } from 'types/productTypes/productType,';
import LoadingSpinner from 'views/common/LoadingSpinner';
import GmarketResultData from './item';

const MarketInGmarket = () => {
  const [keywords, setKeywords] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const [datas, setDatas] = useState<GmarketProducts>({});
  const inputRef = useRef();
  const changeHandler = (e:any) => {
    setKeywords(e.target.value);
  }
  const inputHandler = () => {
    //api요청
    if (!keywords) return;
    setIsLoading(true);
    getGmarketProductData(keywords).then((data) => setDatas(data)).finally(()=>setIsLoading(false))
  }
     return(
       <>
        <div>
           <div className={'flex mb-10 mt-10 justify-center'}>
            <div className='flex max-w-[1000px] w-full shadow-2xl rounded-full p-3'>
                 <SearchInputBasic placeholder="키워드를 입력하세요 (' , '로 구분)" changeEvent={changeHandler} inputEvent={inputHandler} inputRef={inputRef} />
            </div>       
           </div>
           <div className={'mb-20'}>
             {isLoading ? 
             <div className='flex justify-center h-[600px] items-center'> 
              <LoadingSpinner isLoading={isLoading}/>
             </div> :
               <>
                  <GmarketResultData datas={datas}/>
               </> 
            }
           </div>
        </div>
       </>
    )
  } 

 export default MarketInGmarket;