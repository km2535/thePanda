import SearchInputBasic from 'component/searchInput/input';
import React, { useRef, useState } from 'react';
import NaverTopKeyword from './naverTopKeyword';
import CoupangTopKeyword from './coupangTopKeyword';
import GmarketTopKeyword from './gMarketTopKeyword';
import KakaoTopKeyword from './kakaoTopKeyword';

const OptimizationKeyword = () => {
   const inputRef = useRef();
   const [changebleKeyword, setChangebleKeyword] = useState<string>("");
   const [keyword, setKeyword] = useState<string>("");
   const changeHandler = (e:any) => {
       setChangebleKeyword(e.target.value);
    }
   const inputHandler = () => {
      //keyword를 입력하면 모든 props에 전달해서 데이터를 요청해야 한다.
      setKeyword(changebleKeyword)
    }
     return(
       <>
         <div className={'flex mb-10 mt-10 justify-center'}>
            <div className='flex max-w-[600px] w-full shadow-2xl rounded-full p-3'>
             <SearchInputBasic placeholder='키워드를 입력하세요' changeEvent={changeHandler} inputEvent={inputHandler} inputRef={inputRef}/>
            </div>
        </div>
        <div>
           <div>
               <NaverTopKeyword keyword={keyword}/>      
           </div>
           <div>
               <CoupangTopKeyword keyword={keyword}/>      
           </div>
           <div>
               <GmarketTopKeyword keyword={keyword}/>
           </div>
           <div>
               <KakaoTopKeyword keyword={keyword}/>      
           </div>
         </div>
         <div>분석하기 버튼</div>
        <div>아래의 키워드를 클릭하면 키워드가 작성됨</div>
        <div>작성된 키워드의 글자수를 카운트 해줌</div>
        <div>
           <div>네이버 분석 결과</div>
           <div>쿠팡 분석 결과</div>
           <div>g마켓 분석 결과</div>
           <div>카카오 분석 결과</div>
        </div>
       </>
    )
  } 

 export default OptimizationKeyword;