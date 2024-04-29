import React, { useEffect, useState } from 'react';
import { KakaoProducts, ProductKakaoCardData } from 'types/productTypes/productType,';
import KakaoResultDataCard from '../card';
import ModalInKakao from '../modal';
import { CSVLink } from 'react-csv';
import { cardDataProcessKakao } from 'util/cardDataProcessKakao';

const KakaoResultData = ({ datas }: { datas: KakaoProducts }) => {
   const [modal, setModal] = useState("");
  const [keyword, setKeyword] = useState("");
   const modalHandler = (keyword: string) => {
    //modla활성화
    setKeyword(keyword);
    setModal(keyword);
  }
  const [data, setData] = useState<ProductKakaoCardData[] | undefined>([]);
  const [excelData, setExcelData] = useState<any>();
  useEffect(() => {
    const arr: React.SetStateAction<ProductKakaoCardData[] | undefined> = [];
    Object.keys(datas).map((data) =>
    {
      const temp = cardDataProcessKakao(datas[data], data);
      return arr.push(temp);
      }
    ) 
    setData(arr);
  }, [datas, keyword])

  useEffect(() => {
    const header = ["키워드", "총 검색수", 
    "무료배송 상품 수", "신상품 수","최고판매량","평균판매량", "최소판매량", "최고가",
     "평균가","최소가","리뷰0","리뷰1~49","리뷰50~100","리뷰100이상"
  ];
  const result: any[][] = [];
    if (data?.length !== undefined && data.length > 0) {
      data.map((excelData: ProductKakaoCardData) => {
        const exceldatas: string[] = [];
        exceldatas.push(excelData.keyword)
        exceldatas.push(excelData.totalProductsCount+"")
        exceldatas.push(excelData.freeDeliveryCount+"")
        exceldatas.push(excelData.totalNewProductsCount+"")
        exceldatas.push(excelData.maxSalesCount+"")
        exceldatas.push(excelData.aveSalesCount+"")
        exceldatas.push(excelData.minSalesCount+"")
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
          <ModalInKakao setModal={setModal} datas={datas} keyword={keyword} />
        </div>
         }
         <div>
        <div className={'text-right'}>
          {(excelData!== undefined && excelData.length>1) && 
          <CSVLink data={excelData} filename='keyword-analysis(카카오).csv'>
                <button className={'cursor-pointer bg-buttonColor p-2 text-center hover:bg-brandHoverFontColor rounded-xl mt-2 mb-2 text-gray-100'}>키워드 정보다운</button>
            </CSVLink>
          }
        </div>     
        <div className={'flex justify-start flex-wrap'}>
          
            {Object.keys(datas).map((keyword) => (
              <KakaoResultDataCard key={keyword} products={datas[keyword]} keyword={keyword} modalHandler={modalHandler} />
            ))}
          </div>
        </div>
       </>
    )
  } 

 export default KakaoResultData;