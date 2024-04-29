import { optimizationKeyword } from 'apis/request/optimaization';
import React, { useEffect, useState } from 'react';
import parse from 'html-react-parser';
import LoadingSpinner from 'views/common/LoadingSpinner';

// 키워드 검색 결과 props를 받아서 props.keyword를 이용하여 데이터를 받아옴
const GetTopKeyword = ({ keyword, type ,setTotalData}: any) => {
  const [topKeyword, setTopkeyword] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  useEffect(() => {
    if (keyword !== "") {
      setIsLoading(true);
      optimizationKeyword(keyword, type).then((d) => setTopkeyword(d)).finally(() => setIsLoading(false))
    }
  }, [keyword, type])
  useEffect(() => {
    setTotalData((prev: any)=>({...prev,[type]:topKeyword}))
  },[setTotalData, topKeyword, type])
  return (
    <>    
    {isLoading ?
      <div className={"flex justify-center h-full items-center"}>
        <div>
          <LoadingSpinner isLoading={isLoading} />
        </div>
      </div>
      :    
        topKeyword.length === 0 ? "키워드를 검색해주세요" :
        <table>
            <tbody>
        {topKeyword.map((item, i) =>
        <tr key={i}>
          <td className={"text-pretty"}>{parse(item)}</td>
        </tr>
        )}
        </tbody>
      </table>
      }
    </>
  )
}

 export default GetTopKeyword;