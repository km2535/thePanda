import MarketAnalysisPriceChart from 'component/chart/market/price';
import MarketAnalysisReviewChart from 'component/chart/market/review';
import MarketAnalysisSalesCountChart from 'component/chart/market/salesCount';
import MarketAnalysisSatisfyChart from 'component/chart/market/satisfy';
import React, { useEffect, useState } from 'react';
import { GmarketProductItem, ProductGmarketCardData } from 'types/productTypes/productType,';
import { cardDataProcessGmarket } from 'util/cardDataProcessGmarket';

const GmarketResultDataCard = ({ products, keyword, modalHandler}: { products: GmarketProductItem[], keyword: string, modalHandler:any}) => {
  const [data, setData] = useState<ProductGmarketCardData | undefined>();
  useEffect(() => {
     // 데이터 가공
    const temp = cardDataProcessGmarket(products, keyword);
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
     return(
       <div className={'p-2 w-1/3'} onClick={detailHandler} >
   
    {
      data && 
      <div className='p-2 bg-slate-50 rounded-xl max-w-[480px] h-[600px] shadow-lg hover:shadow-2xl hover:scale-110 transition-all cursor-pointer flex flex-col justify-around'>
          <div className={'flex items-center'}>
            <div className={'w-3/5'}>
              <p className={'text-2xl text-center font-extrabold'}>
                {data.keyword}
              </p>
            </div>
            <div className={'text-base w-2/5'}>
              <div className={'flex items-center'}>
                <div className={'w-2/3'}>검색수 </div>
                <div className={'text-2xl w-1/3'}>{data.totalProductsCount}</div> 
              </div>
              <div className={'flex items-center'}>
                <div className={'font-extrabold w-2/3'}>오늘출발</div>
                <div className={'text-2xl w-1/3'}>
                  <span>
                    {data.todayDeliveryCount}
                  </span>
                </div>
              </div>
               <div className={'flex items-center'}>
                <div className={'w-2/3'}>무료배송</div>
                <div className={'text-2xl w-1/3'}>
                  <span>
                    {data.freeDeliveryCount}
                  </span>
                </div>
              </div>
            </div>
          </div>
          <div className={'flex flex-col p-2 justify-center'}>
            <div className={'flex flex-col'}>
              <div className={'flex justify-around m-auto w-full text-center '}>
                <div>가격</div>
                <div>
                  <MarketAnalysisPriceChart minPrice={data.minPrice} maxPrice={data.maxPrice} avePrice={data.avePrice}/>       
                </div>
              </div>
            </div>
            <div className={'flex flex-col'}>
              <div className={'flex justify-around m-auto w-full text-center '}>
                <div>판매량</div>
                <div>
                <MarketAnalysisSalesCountChart aveSales={data.aveSalesCount} maxSales={data.maxSalesCount} minSales={data.minSalesCount}/>       
                </div>
              </div>
            </div>
            
          </div>
          <div className={'flex  p-2'}>
            <div className={'flex flex-col w-1/2 h-3/5'}>
              <div>만족도</div>
              <div className={'w-2/3 m-auto'}>
                <MarketAnalysisSatisfyChart satisfy50minus={data.satisfy50minus} satisfy50to60={data.satisfy50to60} satisfy60to70={data.satisfy60to70} 
                satisfy70to80={data.satisfy70to80}
                       satisfy80to90={data.satisfy80to90} satisfy90to100={data.satisfy90to100} />
              </div>
            </div>
            <div className={'flex flex-col w-1/2 h-3/5'}>
              <div>리뷰건수</div>
              <div className={'w-2/3 m-auto'}>
                <MarketAnalysisReviewChart review0={data.review0} review1to49={data.review1to49} review50to100={data.review50to100} review100plus={data.review100plus}/>
              </div>
            </div>
          </div>
      </div>
  }      
  
        
  </div>
    )
  }  

 export default GmarketResultDataCard;