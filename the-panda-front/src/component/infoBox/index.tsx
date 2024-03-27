import { KeywordData } from 'apis/response/keywordData';
import React, { useEffect, useState } from 'react';
import { InfoTypeForRealTime, categoryType } from 'types/propsTypes/props-types';
import { IoIosArrowForward } from "react-icons/io";
import { categories } from 'types/iconsTypes/icons-types';
import { buttontitle } from 'types/buttonsTypes/button-types';
import { BiSolidDownArrow,BiSolidUpArrow } from "react-icons/bi";

import "./style.css";
//searchType과 selectedCategory를 전달 받아 api를 요청
export default function InfoBox(props: InfoTypeForRealTime) {
  const [category, setCategory] = useState<string>();
  const [title, setTitle] = useState<string>();
  const [cmpData, setcmpData] = useState<categoryType[]>([]);
  const [updatedate, setUpdatedate] = useState<string>();
  useEffect(() => { 
    categories.map((v) => (v.categoryId === props.selectedCategory) ? setCategory(v.category) : "");
    buttontitle.map((v) => (v.typeName === props.searchType) ? setTitle(v.buttonName) : "");
    KeywordData(props, setcmpData, setUpdatedate);
    
  }, [ props])

 
  return (
    <div className='flex bg-backgroundColor  p-10 shadow-2xl rounded-2xl w-full'>
      <div className={'w-full'}>
        <div className='flex items-center'>
          <div className='text-iconsColor font-bold'><span>쇼핑 카테고리</span></div>
          <div ><IoIosArrowForward className={'text-brandFontColor'}/></div>
          <div className='text-iconsColor font-bold'>{ category}</div>
        </div>
        <div className='text-iconsColor font-bold text-2xl h-[30px] mt-[10px] mb-[20px]'> 
          {title}
        </div>
        <div className='text-informationColor text-right'> 
          기준일 : {updatedate} 
        </div>
        <div className='h-[600px] overflow-y-scroll mt-3'> 
          <table className='w-full text-center'>
            <thead className='sticky top-0 bg-backgroundColor z-50'>
            <tr className={'text-informationColor font-thin '}>
              <th className='font-thin sticky'>순위</th>
              <th className='font-thin sticky z-50'>키워드</th>
              <th className='font-thin sticky z-50'>검색량</th>
                <th className='font-thin sticky text-pretty'>경쟁도</th>
                {props.searchType === "rising-keyword" ? 
                  <th className='font-thin sticky'>변동률</th> : 
                <th className='font-thin sticky'>하위카테고리</th> 
                }
            </tr>
            </thead>
            <tbody>
              {cmpData.length <= 0 ? <tr className={'h-28'}>
                <td colSpan={5} rowSpan={10} className={'w-full text-2xl font-bold text-brandFontColor'}>키워드가 존재하지 않습니다.</td>
              </tr> :
            cmpData.map((element,i) => 
              <tr key={i} className={'text-iconsColor font-bold text-lg text-pretty'}>
                <td className={'w-1/12'}>{i+1}</td>
                <td className={'w-4/12 text-pretty'}>{element.keyword}{props.searchType === "new-keyword" && element.is_new ==="true" ? <sup className={"ml-1 text-xs z-0"}><span className='font-bold '>NEW</span></sup>: ""}</td>
                <td className={'w-3/12'}>{element.total_qc_cnt.toString().replace(/\B(?=(\d{3})+(?!\d))/g,',')}</td>
                <td className={'w-1/12'}>{element.comp_idx}</td>
                <td className={'w-3/12 text-sm '}>
                  {props.searchType === "rising-keyword" ? <div className={'flex justify-center'}>{element.diff_total_qc_cnt > 0 ? <> {((element.total_qc_cnt - element.diff_total_qc_cnt)/element.total_qc_cnt).toFixed(2) }<BiSolidUpArrow className='text-green-500 mr-1 ml-1'/></>: element.diff_total_qc_cnt === 0 ? " - " :<> {((element.total_qc_cnt - element.diff_total_qc_cnt)/element.total_qc_cnt).toFixed(2) }<BiSolidDownArrow className='text-red-500 mr-1 ml-1'/></>}</div>:
                  <div className={'flex justify-center'}>
                    {element.category3 !== "" ? <>{element.category4 !== "" ? element.category4: element.category3}</> : element.category2}
                  </div>
                  }
                </td>
              </tr>
              )
            }
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

