
import React, { useEffect, useState } from 'react';
import { CoupangProducts, ProductCoupangCardData } from 'types/productTypes/productType,';
import CoupangResultDataCard from '../card';
import ModalInCoupang from '../modal';
import { CSVLink } from 'react-csv';
import { cardDataProcessCoupang } from 'util/cardDataProcessCoupang';

const CoupangResultData = ({ datas }: { datas: CoupangProducts }) => {
  const [modal, setModal] = useState("");
  const [keyword, setKeyword] = useState("");
  const modalHandler = (keyword: string) => {
    //modla활성화
    setKeyword(keyword);
    setModal(keyword);
  }
  const [data, setData] = useState<ProductCoupangCardData[] | undefined>([]);
  const [excelData, setExcelData] = useState<any>();
  useEffect(() => {
    const arr: React.SetStateAction<ProductCoupangCardData[] | undefined> = [];
    Object.keys(datas).map((data) =>
    {
      const temp = cardDataProcessCoupang(datas[data], data);
      return arr.push(temp);
      }
    ) 
    setData(arr);
  }, [datas, keyword])
  useEffect(() => {
    const header = ["키워드", "총 검색수", 
    "로켓상품수","로켓상품비율", "일반상품수","일반상품비율", "로켓배송", "로켓배송비율",
    "판매자로켓", "판매자로켓비율", "로켓직구", "로켓직구비율", "로켓프레시", "로켓프레시비율",
    "로켓설치", "로켓설치비율", "로켓럭셔리", "로켓럭셔리비율", "배송일 10일 이상", "배송일 10일 이상 비율", "최소가",
    "평균가","최대가","리뷰0","리뷰1~49","리뷰50~100","리뷰100이상"
  ];
  const result: any[][] = [];
    if (data?.length !== undefined && data.length > 0) {
      data.map((excelData: ProductCoupangCardData) => {
        const exceldatas: string[] = [];
        exceldatas.push(excelData.keyword)
        exceldatas.push(excelData.totalProductsCount+"")
        exceldatas.push(excelData.countTotalRocket+"")
        exceldatas.push(excelData.rateCountTotalRocket)
        exceldatas.push(excelData.countRegularProducts+"")
        exceldatas.push(excelData.rateCountRegularProducts)
        exceldatas.push(excelData.countRocketProducts+"")
        exceldatas.push(excelData.rateCountRocketProducts)
        exceldatas.push(excelData.countSellerRocketProducts+"")
        exceldatas.push(excelData.rateCountSellerRocketProducts)
        exceldatas.push(excelData.countRocketDirectProducts+"")
        exceldatas.push(excelData.rateCountRocketDirectProducts)
        exceldatas.push(excelData.countRocketFreshProducts+"")
        exceldatas.push(excelData.rateCountRocketFreshProducts)
        exceldatas.push(excelData.countRocketInstallProducts+"")
        exceldatas.push(excelData.rateCountRocketInstallProducts)
        exceldatas.push(excelData.countRocketLuxuryProducts+"")
        exceldatas.push(excelData.rateCountRocketLuxuryProducts)
        exceldatas.push(excelData.deliveryLongDate+"")
        exceldatas.push(excelData.rateDeliveryLongDate)
        exceldatas.push(excelData.minPrice+"")
        exceldatas.push(excelData.maxPrice+"")
        exceldatas.push(excelData.avePrice+"")
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
  return (
     <>
      {modal &&
        <div>
          <ModalInCoupang setModal={setModal} datas={datas} keyword={keyword} />
        </div>
      }
      <div>
        <div className={'text-right'}>
          {(excelData!== undefined && excelData.length>1) && 
          <CSVLink data={excelData} filename='keyword-analysis(쿠팡).csv'>
                <button className={'cursor-pointer bg-buttonColor p-2 text-center hover:bg-brandHoverFontColor rounded-xl mt-2 mb-2 text-gray-100'}>키워드 정보다운</button>
            </CSVLink>
          }
        </div>
        <div className={'flex justify-start flex-wrap'}>
          
            {Object.keys(datas).map((keyword) => (
              <CoupangResultDataCard key={keyword} products={datas[keyword]} keyword={keyword} modalHandler={modalHandler} />
            ))}
          </div>
          </div>
        </>
    )
  } 

 export default CoupangResultData;