import React, { useEffect, useState } from 'react';
import { GmarketProducts, ProductGmarketCardData } from 'types/productTypes/productType,';
import GmarketResultDataCard from '../card';
import ModalInGmarket from '../modal';
import { cardDataProcessGmarket } from 'util/cardDataProcessGmarket';
import { CSVLink } from 'react-csv';

const GmarketResultData = ({ datas }: { datas: GmarketProducts }) => {
  const [modal, setModal] = useState("");
  const [keyword, setKeyword] = useState("");
   const modalHandler = (keyword: string) => {
    //modla활성화
    setKeyword(keyword);
    setModal(keyword);
  }
  // 엑셀 데이터 작업 
  const [data, setData] = useState<ProductGmarketCardData[] | undefined>([]);
  const [excelData, setExcelData] = useState<any>();
  useEffect(() => {
    const arr: React.SetStateAction<ProductGmarketCardData[] | undefined> = [];
    Object.keys(datas).map((data) =>
    {
      const temp = cardDataProcessGmarket(datas[data], data);
      return arr.push(temp);
      }
    ) 
    setData(arr);
  }, [datas, keyword])
  useEffect(() => {
    const header = ["키워드", "총 검색수", 
    "오늘배송 상품 수","무료배송 상품 수", "최고판매량","평균판매량", "최소판매량", "만족도90%이상",
    "만족도80%이상", "만족도70%이상", "만족도60%이상", "만족도50%이상", "만족도50%미만", "최고가",
     "평균가","최소가","리뷰0","리뷰1~49","리뷰50~100","리뷰100이상"
  ];
  const result: any[][] = [];
    if (data?.length !== undefined && data.length > 0) {
      data.map((excelData: ProductGmarketCardData) => {
        const exceldatas: string[] = [];
        exceldatas.push(excelData.keyword)
        exceldatas.push(excelData.totalProductsCount+"")
        exceldatas.push(excelData.todayDeliveryCount+"")
        exceldatas.push(excelData.freeDeliveryCount+"")
        exceldatas.push(excelData.maxSalesCount+"")
        exceldatas.push(excelData.aveSalesCount+"")
        exceldatas.push(excelData.minSalesCount+"")
        exceldatas.push(excelData.satisfy90to100+"")
        exceldatas.push(excelData.satisfy80to90+"")
        exceldatas.push(excelData.satisfy70to80+"")
        exceldatas.push(excelData.satisfy60to70+"")
        exceldatas.push(excelData.satisfy50to60+"")
        exceldatas.push(excelData.satisfy50minus+"")
        exceldatas.push(excelData.maxPrice+"")
        exceldatas.push(excelData.avePrice+"")
        exceldatas.push(excelData.minPrice+"")
        exceldatas.push(excelData.review0+"")
        exceldatas.push(excelData.review1to49+"")
        exceldatas.push(excelData.review50to100+"")
        exceldatas.push(excelData.review100plus+"")
        return result.push(exceldatas);
      })
    }
    result.unshift(header)
    setExcelData(result);
  }, [data])
     return(
       <>
          {modal &&
        <div>
          <ModalInGmarket setModal={setModal} datas={datas} keyword={keyword} />
        </div>
         }
         <div>
        <div className={'text-right'}>
          {(excelData!== undefined && excelData.length>1) && 
          <CSVLink data={excelData} filename='keyword-analysis(지마켓).csv'>
                <button className={'cursor-pointer bg-buttonColor p-2 text-center hover:bg-brandHoverFontColor rounded-xl mt-2 mb-2 text-gray-100'}>키워드 정보다운</button>
            </CSVLink>
          }
        </div>   
        <div className={'flex justify-start flex-wrap'}>
          
            {Object.keys(datas).map((keyword) => (
              <GmarketResultDataCard key={keyword} products={datas[keyword]} keyword={keyword} modalHandler={modalHandler} />
            ))}
          </div>
        </div>
       </>
    )
  } 

 export default GmarketResultData;