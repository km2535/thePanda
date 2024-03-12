import { KeywordData } from 'apis/response/keywordData';
import React, { useEffect, useState } from 'react';
import { InfoTypeForRealTime, categoryType } from 'types/propsTypes/props-types';
import { IoIosArrowForward } from "react-icons/io";
import { categories } from 'types/iconsTypes/icons-types';
import { buttontitle } from 'types/buttonsTypes/button-types';
import { mergeObjects } from 'util/mergeObjects';
import filterData from 'util/filterData';

//searchType과 selectedCategory를 전달 받아 api를 요청
export default function InfoBox(props: InfoTypeForRealTime) {
  const [category, setCategory] = useState<string>();
  const [title, setTitle] = useState<string>();
  const [cmpData, setcmpData] = useState<categoryType[]>([]);
  const [categoriesData, setCategoriesData] = useState<categoryType[]>([]);
  const [updatedate, setUpdatedate] = useState<string>();
  const [newdata, setNewData] = useState<categoryType[]>([]);
  useEffect(() => { 
    categories.map((v) => (v.categoryId === props.selectedCategory) ? setCategory(v.category) : "");
    buttontitle.map((v) => (v.typeName === props.searchType) ? setTitle(v.buttonName) : "");
    KeywordData(props,setcmpData,setCategoriesData,setUpdatedate);
  }, [props])
  useEffect(() => {
    setNewData(mergeObjects(categoriesData, cmpData))
  }, [categoriesData, cmpData])
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
        <div className='text-informationColor'> 
          {updatedate}
        </div>
        <div className='h-[600px] overflow-y-scroll '> 
          <table className='w-full text-center'>
            <thead className='sticky top-0 bg-backgroundColor'>
            <tr className={'text-informationColor font-thin '}>
              <th className='font-thin sticky'>순위</th>
              <th className='font-thin sticky'>키워드</th>
              <th className='font-thin sticky'>검색량</th>
              <th className='font-thin sticky'>경쟁강도</th>
              <th className='font-thin sticky'>하위카테고리</th>
            </tr>
            </thead>
            <tbody>
            {newdata.map((element,i) => 
              <tr key={i} className={'text-iconsColor font-bold text-lg'}>
                <td className={'text-iconsColor'}>{i+1}</td>
                <td className={'text-iconsColor'}>{element.keyword}</td>
                <td>{element.total_qc_cnt}</td>
                <td>{element.comp_idx}</td>
                <td> {element.category3} {element.category4} </td>
              </tr>
            )}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

