import { getCoupangProductData } from 'apis/request/analysis/market';
import SearchInputBasic from 'component/searchInput/input';
import React, { useRef, useState } from 'react';
import { CoupangProducts } from 'types/productTypes/productType,';
import CoupangResultData from './item';
import LoadingSpinner from 'views/common/LoadingSpinner';

const MarketInCoupang = () => {
  // state에 검색 결과를 저장하고 검색결과가 변화하지 않으면 api요청을 하지 않는다..
  // 검색 버튼을 누를 때만 api를 요청한다. 
  //엑셀을 다운 받을 수 있도록 만들어 준다. 
  const [keywords, setKeywords] = useState("");
  const [datas, setDatas] = useState<CoupangProducts>({});
  const inputRef = useRef();
  const [isLoading, setIsLoading] = useState(false);
  const changeHandler = (e:any) => {
    setKeywords(e.target.value);
  }
  const inputHandler = () => {
    //api요청
    if (!keywords) return;
    setIsLoading(true);
    getCoupangProductData(keywords).then((data) => setDatas(data)).finally(()=>setIsLoading(false))
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
               
                <CoupangResultData datas={datas}/>
              
            }
           </div>
        </div>
       </>
    )
  } 

 export default MarketInCoupang;