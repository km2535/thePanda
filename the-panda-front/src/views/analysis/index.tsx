
import ReviewAnalysisInCoupang from 'apis/response/coupang/reviewAnalysis';
 import SearchInputBasic from 'component/searchInput/input';
import React, { useEffect, useRef, useState } from 'react';
import { CSVLink } from 'react-csv';
import { HashLoader } from 'react-spinners';

interface ReviewData {
  [key: string]: {
    [key: string]: string;
  };
}
const AnalysisProductToCoupang = () => {
  //여기서 키워드는 실제 상품 번호를 의미..
  const [inputState, setInputState] = useState({ keyword: "", reviewCount: 10 });
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const [duplicateProductData, setDuplicateProductData] = useState<any>([]);
  const [duplicateUserData, setDuplicateUserData] = useState<any>([]);
  const [duplicateData, setDuplicateData] = useState<any>([]);
  const [data, setData] = useState<ReviewData>({});
  const inputRef = useRef();
  const changeEventHandler = (e: any) => { 
    if (e.target.id === 'reviewCount') {
      let number = e.target.value;
      e.target.value = number % 5 === 0 ? number : Math.round(number / 5) * 5 ;
    } else {
      e.target.value = e.target.value.trim();
    }
    setInputState((prev)=>({...prev, [e.target.id]:e.target.value}))
  }  
  const inputEventHandler = () => {
    //상품 번호가 아니라면, 상품 번호에 띄어쓰기가 있다면 검사해서 에러를 핸들링함.
    setIsLoading(true);
    ReviewAnalysisInCoupang(inputState.keyword, inputState.reviewCount, setData).then((d) => setData(d)).then(() => setIsLoading(false));
  // api 요청 
  }
  useEffect(() => {
    const header = ["상품명", "옵션1", "옵션2","옵션3","옵션4","작성자", "등록일","중복횟수"];
    const header2 = ["작성자", "중복횟수"];
    const result:string[][] = [];
    if (Object.keys(data).length > 0) {
      Object.keys(data).map(person =>
        Object.keys(data[person]).map((title) => {
          const exceldatas: string[] = [];
          const product = title.split(",");
          exceldatas.push(product[0] || "");
          exceldatas.push(product[1] || "");
          exceldatas.push(product[2] || "");
          exceldatas.push(product[3] || "");
          exceldatas.push(product[4] || "");
          exceldatas.push(person)
          exceldatas.push(data[person][title])
          result.push(exceldatas);
          return result;
        })
        
      )
    } 
    addDuplicateCounts(result);
    result.sort(compare);
    result.unshift(header); 
    setDuplicateProductData([...result]);
    setDuplicateData(result);
    userDuplicate(result).forEach((item) =>result.unshift(item))
    result.unshift(header2); 
  }, [data])
  
  

  // 상품의 중복횟수를 구하는 함수 
  const addDuplicateCounts = (data: any[]) => {
    const counts: any = {}; // 상품명의 중복 횟수를 저장할 객체
    // 각 사용자별 상품명 중복 횟수 계산
    const userProductCounts: any = {}; // 각 사용자별 상품명의 중복 횟수를 저장할 객체
    data.forEach((item: any[]) => {
      const productName = item[0]; // 상품명은 첫 번째 요소에 위치
      const userId = item[5]; // 사용자 아이디는 여섯 번째 요소에 위치
      
        // 해당 사용자의 상품명이 중복되지 않은 경우에만 중복 횟수를 계산
        if (!(userId in userProductCounts && userProductCounts[userId].includes(productName))) {
            counts[productName] = (counts[productName] + 1 || 0); // 중복 횟수 증가
        }
        
        // 해당 사용자의 상품명 배열에 현재 상품명 추가
        if (!(userId in userProductCounts)) {
          userProductCounts[userId] = [];
        }
        userProductCounts[userId].push(productName);
      });
      
    // 각 마지막 요소에 중복 횟수를 추가
    data.forEach((item: any[]) => {
        const productName = item[0]; // 상품명은 첫 번째 요소에 위치
        const duplicateCount = counts[productName]; // 해당 상품명의 중복 횟수
        item.push(duplicateCount); // 중복 횟수를 마지막 요소에 추가
    });
    return data;
  }
  
  //비교함수
const compare = (a: string[], b: string[]) => {
  const duplicateCountA = parseInt(a[a.length-1]); // 첫 번째 배열의 중복 횟수
    const duplicateCountB = parseInt(b[b.length-1]); // 두 번째 배열의 중복 횟수

    // 중복 횟수를 기준으로 정렬
    if (duplicateCountA > duplicateCountB) {
        return -1;
    } else if (duplicateCountA < duplicateCountB) {
        return 1;
      } else {
        return 0;
      }
    }
    
  // 중복된 횟수가 있는 유저 정리 
  const userDuplicate = (data: any[]) => {
    const header = ["작성자", "중복횟수"];
    const result:string[][] = [];
    const counts: any = {};

    data.forEach(item => {
        const userId = item[5]; // 사용자 아이디는 여섯 번째 요소에 위치
        if (item[7] > 0) {
          
          // 중복 구매된 상품의 수를 계산
          counts[userId] = (counts[userId] + 1 || 1);
        }
      });

      // 사용자별 중복 구매된 상품의 수를 배열에 추가
      for (const userId in counts) {
        const exceldatas: string[] = [];
        exceldatas.push(userId);
        exceldatas.push(counts[userId]);
        result.push(exceldatas);
    }
    result.unshift(header)
    setDuplicateUserData(result);
    return result;
  }

   return (
     <>
      <div>
        <div className={'shadow-2xl border-t-1 border-b-1 rounded-full w-4/5 m-auto mt-10'}>
           <div className={'flex justify-center w-4/5 h-[80px] align-middle m-auto'}>
             <input type="number" name="" id="reviewCount" defaultValue={10} step={5} min={5} max={60} onChange={changeEventHandler}/> 
            <SearchInputBasic placeholder='상품 번호를 입력하세요' inputRef={inputRef} inputEvent={inputEventHandler}  changeEvent={changeEventHandler} />
          </div>
         </div>         
         <div className={'text-right  sticky top-[100px] z-20'}>
           {duplicateProductData.length > 3 ?
             <CSVLink data={duplicateData} filename='coupand-analysis.csv'>
               <button className={'cursor-pointer bg-buttonColor p-2 text-center hover:bg-brandHoverFontColor rounded-xl mt-2 mb-2 text-gray-100'}>엑셀로 다운받기</button>
             </CSVLink> :
             <CSVLink data={duplicateData}>
               <button className={'bg-buttonColor p-2 text-center rounded-xl mt-2 mb-2 text-gray-100'} disabled>엑셀로 다운받기</button>
             </CSVLink>}
         </div>
         <div>
           <div className={'m-auto w-1/2'}>
             <div className='w-full shadow-lg rounded-lg max-h-[500px] overflow-y-scroll mx-4 md:mx-10 mb-8 m-auto'>
               <table className='w-full table-fixed'>
                 {duplicateUserData?.map((item: any) =>
                   item[0] === '작성자' ?
                      <thead className='sticky top-0 bg-backgroundColor z-10'>
                       <tr>
                        <th className='text-gray-600'>작성자</th>
                        <th className='text-gray-600'>중복횟수</th>
                       </tr>    
                      </thead>:
                          
                      <tbody >
                          <tr >
                            <td className={'w-1/2 py-4 px-6 text-left text-gray-600 font-bold uppercase'}>{item[0]}</td>
                            <td className={'w-1/2 py-4 px-6 text-left text-gray-600 font-bold uppercase'}>{item[1]}</td>
                          </tr>
                      </tbody>
            )}
               </table> 
              </div>
            </div>
             <div className='shadow-lg rounded-lg max-h-[800px] overflow-y-scroll mx-4 md:mx-10 mb-8'>
               <table className='w-full table-fixed '>
                
                 {duplicateProductData?.map((item: any) =>
                 
                   item[0] === '상품명' ?
                     <thead className='sticky top-0 bg-backgroundColor z-50'>
                       <tr>
                         <td className='w-3/12 py-4 text-pretty px-6 text-left text-gray-600 font-bold uppercase'>
                            상품명
                          </td>
                         <td className='w-1/12 py-4 text-pretty px-6 text-left text-gray-600 font-bold uppercase'>
                            옵션1
                          </td>
                         <td className='w-1/12 py-4 text-pretty px-6 text-left text-gray-600 font-bold uppercase'>
                            옵션2
                          </td>
                         <td className='w-1/12 py-4 text-pretty px-6 text-left text-gray-600 font-bold uppercase'>
                            옵션3
                          </td>
                         <td className='w-1/12 py-4 text-pretty px-6 text-left text-gray-600 font-bold uppercase'>
                            옵션4
                          </td>
                         <td className='w-1/12 py-4 text-pretty px-6 text-left text-gray-600 font-bold uppercase'>
                            작성자
                          </td>
                         <td className='w-1/12 py-4 text-pretty px-6 text-left text-gray-600 font-bold uppercase'>
                            등록일
                          </td>
                          <td className='w-1/12 py-4 text-pretty px-6 text-left text-gray-600 font-bold uppercase'>
                              중복횟수
                            </td>
                      </tr>
                    </thead>
                     :
                     <tbody>
                       <tr>
                          <td className='w-3/12 py-4 text-pretty px-6 text-left text-gray-600 font-bold uppercase'>
                            {item[0]}
                          </td>
                          <td className='w-1/12 py-4 text-pretty px-6 text-left text-gray-600 font-bold uppercase'>
                            {item[1]}
                          </td>
                          <td className='w-1/12 py-4 text-pretty px-6 text-left text-gray-600 font-bold uppercase'>
                            {item[2]}
                          </td>
                          <td className='w-1/12 py-4 text-pretty px-6 text-left text-gray-600 font-bold uppercase'>
                            {item[3]}
                          </td>
                          <td className='w-1/12 py-4 text-pretty px-6 text-left text-gray-600 font-bold uppercase'>
                            {item[4]}
                          </td>
                          <td className='w-1/12 py-4 text-pretty px-6 text-left text-gray-600 font-bold uppercase'>
                            {item[5]}
                          </td>
                          <td className='w-1/12 py-4 text-pretty px-6 text-left text-gray-600 font-bold uppercase'>
                            {item[6]}
                          </td>
                          <td className='w-1/12 py-4 text-pretty px-6 text-left text-gray-600 font-bold uppercase'>
                            {item[7]}
                          </td>
                       </tr>
                     </tbody>
                 
            )}
               </table>
               
           </div>
         </div>
       </div>
       {/* 스핀로더 */}
       {isLoading && 
       <div className='absolute top-0 left-0 w-full h-svh flex justify-center items-center overflow-hidden bg-opacity-70 backdrop-blur-sm m-auto'>
          <HashLoader color="#001C34" loading={isLoading}/>
       </div>
      }
     </>
    )
  } 

 export default AnalysisProductToCoupang;