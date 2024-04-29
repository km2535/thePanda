import MarketAnalysisPriceChart from 'component/chart/market/price';
import MarketAnalysisReviewChart from 'component/chart/market/review';
import React, { useEffect, useState } from 'react';
import { CoupangProductItem, ProductCoupangCardData } from 'types/productTypes/productType,';
import {  cardDataProcessCoupang } from 'util/cardDataProcessCoupang';

const CoupangResultDataCard = ({ products, keyword,modalHandler }: { products: CoupangProductItem[] , keyword:string,modalHandler:any}) => {
  const [data, setData] = useState<ProductCoupangCardData | undefined>();
  useEffect(() => {
    const temp = cardDataProcessCoupang(products, keyword);
    setData(temp);
  }, [products, keyword])
  const detailHandler = () => {
    //모달 창 활성화
    modalHandler(keyword);
    window.scrollTo({
      top: 0,
      behavior: 'smooth',
    });
}
  return (
  <div className={'p-2 w-1/3 '} onClick={detailHandler} >
   
    {
      data && 
      <div className='p-2 bg-slate-50 rounded-xl max-w-[480px] h-[580px] shadow-lg hover:shadow-2xl hover:scale-110 transition-all cursor-pointer flex flex-col justify-around'>
          <div className={'flex items-center'}>
            <div className={'w-3/5'}>
              <p className={'text-2xl text-center font-extrabold'}>
                {data.keyword}
              </p>
            </div>
            <div className={'text-base'}>
              <div className={'flex items-center'}>
                <div className={'w-2/3'}>총 검색수 </div>
                <div className={'text-2xl w-1/3'}>{data.totalProductsCount}</div> 
              </div>
              <div className={'flex items-center'}>
                <div className={'font-extrabold w-2/3'}>로켓</div>
                <div className={'text-2xl w-1/3'}>
                  <span>
                    {data.countTotalRocket}
                  </span>
                <span className={'text-sm'}>({data.rateCountTotalRocket}%)</span>
                </div>
              </div>
               <div className={'flex items-center'}>
                <div className={'w-2/3'}>일반</div>
                <div className={'text-2xl w-1/3'}>
                  <span>
                    {data.countRegularProducts}
                  </span>
                <span className={'text-sm'}>({data.rateCountRegularProducts}%)</span>
                </div>
              </div>
            </div>
          </div>
          <div className={'flex flex-col'}>
            <div className={'flex justify-around mb-3'}>
              <div className={'w-1/3 text-center text-lg'}>
                <div className={'bg-rocketDelivery bg-contain bg-no-repeat bg-center w-full h-5'}>
                </div>
                <div>
                  <span className='text-xl m-1'>
                    {data.countRocketProducts}
                    <span className={'text-sm ml-1'}>건</span>
                  </span>
                  <span className='text-sm'>
                    {data.rateCountRocketProducts}%
                  </span>
                </div>
              </div>            
              <div className={'w-1/3 text-center text-lg'}>
                <div className={'bg-sellerRocket bg-contain bg-no-repeat bg-center w-full h-5'}>
                </div>
                <div>
                  <span className='text-xl m-1'>
                    {data.countSellerRocketProducts}
                    <span className={'text-sm ml-1'}>건</span>
                  </span>
                  <span className='text-sm'>
                    {data.rateCountSellerRocketProducts}%
                  </span>
                </div>
              </div>            
              <div className={'w-1/3 text-center text-lg'}>
                <div className={'bg-rocketDirectly bg-contain bg-no-repeat bg-center w-full h-5'}>
                </div>
                <div>
                  <span className='text-xl m-1'>
                    {data.countRocketDirectProducts}
                    <span className={'text-sm ml-1'}>건</span>
                  </span>
                  <span className='text-sm'>
                    {data.rateCountRocketDirectProducts}%
                  </span>
                </div>
              </div>            
          </div>
          <div className={'flex justify-around'}>
            <div className='w-1/3 text-center text-lg'>
                <div className={'bg-rocketFresh bg-contain bg-no-repeat bg-center w-full h-5'}>
                </div>
                <div>
                  <span className='text-xl m-1'>
                    {data.countRocketFreshProducts}
                    <span className={'text-sm ml-1'}>건</span>
                  </span>
                  <span className='text-sm'>
                    {data.rateCountRocketFreshProducts}%
                  </span>
                </div>
            </div>            
              <div className='w-1/3 text-center text-lg'>
                <div className={'bg-rocketInstall bg-contain bg-no-repeat bg-center w-full h-5'}>
                </div>
                <div>
                  <span className='text-xl m-1'>
                  {data.countRocketInstallProducts}
                    <span className={'text-sm ml-1'}>건</span>
                  </span>
                  <span className='text-sm'>
                    {data.rateCountRocketInstallProducts}%
                  </span>
                </div>
            </div>            
              <div className='w-1/3 text-center text-lg'>
                <div className={'w-full h-5 bg-rocketLuxury bg-contain bg-no-repeat bg-center'}>
                </div>
                <div>
                  <span className='text-xl m-1'>
                    {data.countRocketLuxuryProducts}
                    <span className={'text-sm ml-1'}>건</span>
                  </span>
                  <span className='text-sm'>
                   {data.rateCountRocketLuxuryProducts}%
                  </span>
                </div>
            </div>  
          </div>
          </div>
          <div className={'flex justify-around'}>
              <div className={'flex w-full ml-7'}>
                <div className={'w-1/3'}>
                  <p className={'text-xl'}>배송일 10+</p>
                </div>
                <div >
                  <span className='text-xl m-1'>
                    {data.deliveryLongDate}
                  </span>
                  <span className='text-sm'>
                    {data.rateDeliveryLongDate}%
                  </span>
                </div>
            </div>
          </div>
          <div className={'flex p-2'}>
            <div className={'w-1/2'}>
              <span>가격</span>
              <div>
                <MarketAnalysisPriceChart minPrice={data.minPrice} maxPrice={data.maxPrice} avePrice={data.avePrice}/>
              </div>
            </div>
            <div className={'w-1/2'}>
              <span>리뷰건수</span>
              <div className={'p-2'}>
                <MarketAnalysisReviewChart review0={data.review0} review1to49={data.review1to49} review50to100={data.review50to100} review100plus={data.review100plus}/>
              </div>
            </div>
          </div>
      </div>
  }      
  
        
  </div>
  )
} 

 export default CoupangResultDataCard;