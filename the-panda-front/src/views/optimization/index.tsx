import SearchInputBasic from 'component/searchInput/input';
import React, { useEffect, useRef, useState } from 'react';
import GetTopKeyword from './topKeyword';
import parse from 'html-react-parser';
import { duplicateInspect } from 'apis/request/optimaization/duplicateInspect';
import { duplicateOtherResult } from 'apis/request/optimaization/duplicateOtherResult';
import { RiFileCopy2Line } from "react-icons/ri";
import { FaCheckSquare } from "react-icons/fa";

interface DuplicateSame {
  [platform: string]: { [keyword: string]: [] };
}
interface ProcessedData {
  [platform: string]: { [keyword: string]: number };
}

const OptimizationKeyword = () => {
   const inputRef =  useRef<HTMLInputElement | null>(null);
   const [isCopy, setIsCopy] = useState(false);
   const [isSearch, setIsSearch] = useState(false);
   const [newKeyword, setNewKeyword] = useState("");
   const [keywordCnt, setKeywordCnt] = useState(0);
   const [totalData, setTotalData] = useState({
    "naver": [],
    "coupang": [],
    "gmarket": [],
    "kakao": []
});
   const [duplicateSame, setDuplicateSame] = useState<DuplicateSame>({});
   const [duplicateOther, setDuplicateOther] = useState<ProcessedData>({});
   const [changebleKeyword, setChangebleKeyword] = useState<string>("");
   const [keyword, setKeyword] = useState<string>("");
   const changeHandler = (e:any) => {
       setChangebleKeyword(e.target.value);
    }
   const inputHandler = () => {
      //keyword를 입력하면 모든 props에 전달해서 데이터를 요청해야 한다.
      setKeyword(changebleKeyword)
   }
   const analysisBtn = () => {
      if (totalData.naver.length !== 0
         || totalData.coupang.length !== 0
         || totalData.gmarket.length !== 0
         || totalData.kakao.length !== 0) {
            duplicateInspect(totalData).then((d) => {
               setDuplicateSame(d);
               setDuplicateOther(duplicateOtherResult(d));
            })   
      } else {
         if (inputRef.current) {
            setIsSearch(true);
            inputRef.current.focus();
             setTimeout(() => setIsSearch(false), 1500);
         }
      }
   }
   const keywordClickHandler = (e: any) => {
      if (newKeyword === "") {
         setNewKeyword(() => e.target.innerHTML)
      } else {
         setNewKeyword((prev) => prev + " " + e.target.innerHTML)
      }
   }
  const handleInputChange = (e:any) => {
        setNewKeyword(e.target.value);
   };
   const copyHandler = () => {
      if (newKeyword.length !== 0) { 
         setIsCopy(true)
         navigator.clipboard.writeText(newKeyword);
         setTimeout(() => setIsCopy(false), 1500);
      }
   };
   useEffect(() => {
      const characterCount = newKeyword.length;
      setKeywordCnt(characterCount);
   },[newKeyword])
     return(
       <>
         <div className={'flex mb-10 mt-10 justify-center'}>
            <div className='flex max-w-[600px] w-full shadow-2xl rounded-full p-3'>
                 <SearchInputBasic placeholder='키워드를 입력하세요' changeEvent={changeHandler} inputEvent={inputHandler} inputRef={inputRef} />
            </div>
           </div>
           {isSearch &&
              <div className={'w-full text-center mb-10 '}>
               <span className={' text-red-600 text-[20px]'}>키워드 검색을 먼저 해주세요</span>
              </div>
           }
         <div className={"flex max-w-[1400px] w-full"}>
           <div className={'w-1/4'}>
              <div className={"text-xl font-bold text-center"}>네이버</div>
              <div className={"m-1 p-4 text-[14px] text-pretty overflow-y-scroll h-[600px] bg-slate-100 rounded-xl shadow-lg"}>
                    <GetTopKeyword keyword={keyword} type={"naver"} setTotalData={setTotalData } />      
              </div>
           </div>
           <div className={'w-1/4'}>
              <div className={"text-xl font-bold text-center"}>쿠팡</div>
              <div className={"m-1 p-4 text-[14px] text-pretty overflow-y-scroll h-[600px] bg-slate-100 rounded-xl shadow-lg"}>
                    <GetTopKeyword keyword={keyword} type={"coupang"} setTotalData={setTotalData} />      
              </div>
           </div>
           <div className={'w-1/4'}>
              <div className={"text-xl font-bold text-center"}>지마켓</div>
              <div className={"m-1 p-4 text-[14px] text-pretty overflow-y-scroll h-[600px] bg-slate-100 rounded-xl shadow-lg"}>
                <GetTopKeyword keyword={keyword} type={"gmarket"} setTotalData={setTotalData} />      
              </div>
           </div>
           <div className={'w-1/4'}>
              <div className={"text-xl font-bold text-center"}>카카오</div>
              <div className={"m-1 p-4 text-[14px] text-pretty overflow-y-scroll h-[600px] bg-slate-100 rounded-xl shadow-lg"}>
                <GetTopKeyword keyword={keyword} type={"kakao"} setTotalData={setTotalData} />      
              </div>
           </div>
           
         </div>
          <div className={'text-center mt-5 mb-5'}>
            <button
               onClick={analysisBtn}
               className="bg-blue-500 hover:bg-blue-700 text-white font-semibold py-2 px-4 rounded cursor-pointer"
            >
               분석하기
            </button>
         </div>        
        <div className={'flex'}>
           {
               Object.entries(duplicateSame)
                  .map(([platform, values]) =>
                        <div className={'flex flex-col w-1/4'} key={platform}>
                        <div className={"uppercase"}>{platform}</div>
                        <div className={'w-full pr-1 pl-1 text-[14px] text-pretty overflow-y-scroll h-[600px] bg-slate-100 rounded-xl shadow-lg mb-20'}>
                           <table className='w-full'>
                              <thead className={'sticky top-0 bg-backgroundColor z-50 h-10'}>
                                 <tr>
                                    <th>키워드</th>
                                    <th>중복횟수</th>
                                 </tr>
                              </thead>
                              <tbody>
                              {
                              Object.entries(values)
                                 .sort((a, b) => parseInt(b[0]) - parseInt(a[0]))
                                 .map(([key, value]) => 
                                       value.map((item) => {
                                             const keyword = (item + "").replace(/<[^>]*>/g, '');
                                             const count = duplicateOther[platform][keyword] || 0;
                                             let backgroundColor = '';
                                             // 조건에 따라 폰트와 배경 색상 설정
                                             if (count >= 3) {
                                                backgroundColor = '#8FABDB';
                                             } else if (count === 2) {
                                                backgroundColor = '#BECCED';
                                             } else if (count === 1) {
                                                backgroundColor = '#D0E9F9';
                                             }
                                          return (
                                                <tr style={{ backgroundColor }}>
                                                   <td className='w-4/5 mx-auto text-center cursor-pointer hover:font-extrabold hover:text-red-700' onClick={keywordClickHandler}>{parse(item)}</td>
                                                      <td className={'text-center'}>{key}</td>
                                                </tr>
                                             );
                                          })
                                       )
                                    }
                                 </tbody>
                              </table>
                           </div>
                        </div>
                  )
            }

           </div>
           <div className={'mb-20'}>
              <div className='flex items-center'>
                 <div className={'w-auto flex-grow'}>
                    <textarea
                       className={'border-slate-600 border-2 border-solid w-full min-w-[500px] text-xl'}
                    value={newKeyword} onChange={handleInputChange}/>
                 </div> 
                 <div >
                     <div onClick={copyHandler} className={'cursor-pointer'}>
                        {isCopy ? 
                        <div className='flex items-center text-xl'>
                              <FaCheckSquare className='text-green-600 mr-2 ml-2'/>
                              복사완료
                           </div>
                           :
                           <div className={'text-[30px] text-gray-600 hover:text-gray-900'}>
                                 <RiFileCopy2Line/>
                           </div>
                        }
                        </div>
                        <div>
                        글자수 : <span className={'text-[20px] font-extrabold'}>{keywordCnt}</span>
                        </div>
                  </div>
               </div>
           </div>
       </>
    )
  } 

 export default OptimizationKeyword;