import MarketAnalysisPriceChart from 'component/chart/market/price';
import MarketAnalysisReviewChart from 'component/chart/market/review';
import MarketAnalysisSalesCountChart from 'component/chart/market/salesCount';
import React, { useEffect, useState } from 'react';
import { KakaoProductItem, ProductKakaoCardData } from 'types/productTypes/productType,';
import { cardDataProcessKakao } from 'util/cardDataProcessKakao';
const KakaoResultDataCard = ({ products, keyword, modalHandler }: { products: KakaoProductItem[], keyword: string, modalHandler: any }) => {
   const [data, setData] = useState<ProductKakaoCardData | undefined>();
  useEffect(() => {
     // 데이터 가공
    const temp = cardDataProcessKakao(products, keyword);
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
       <div className={'p-2 w-1/3'}  onClick={detailHandler}>
   
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
                <div className={'font-extrabold w-2/3'}>무료배송</div>
                <div className={'text-2xl w-1/3'}>
                  <span>
                    {data.freeDeliveryCount}
                  </span>
                </div>
              </div>
               <div className={'flex items-center'}>
                <div className={'w-2/3'}>신상품</div>
                <div className={'text-2xl w-1/3'}>
                  <span>
                    {data.totalNewProductsCount}
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
                   {data.top1Sales !== 0 && 
            <div className={"w-1/2"}>
                     <table className={'text-sm text-nowrap w-full'}>
                       <caption >{"TOP5 매출 (단위 : 만원)"}</caption>
                       <tbody>
                         <tr>
                           <td><a href={data.top1storeLinkPath} target='_blank' rel="noreferrer" className={'hover:font-extrabold'}>{ data.top1StoreName}</a></td>
                           <td className={"text-right"}>{((data.top1Sales)/10000).toFixed(0)}</td>
                         </tr>
                         <tr>
                           <td><a href={data.top2storeLinkPath} target='_blank' rel="noreferrer" className={'hover:font-extrabold'} >{ data.top2StoreName}</a></td>
                           <td className={"text-right"}>{((data.top2Sales)/10000).toFixed(0)}</td>
                         </tr>
                         <tr>
                           <td><a href={data.top3storeLinkPath} target='_blank' rel="noreferrer" className={'hover:font-extrabold'} >{ data.top3StoreName}</a></td>
                           <td className={"text-right"}>{((data.top3Sales)/10000).toFixed(0)}</td>
                         </tr>
                         <tr>
                           <td><a href={data.top4storeLinkPath} target='_blank' rel="noreferrer" className={'hover:font-extrabold'}>{ data.top4StoreName}</a></td>
                           <td className={"text-right"}>{((data.top4Sales)/10000).toFixed(0)}</td>
                         </tr>
                         <tr>
                           <td><a href={data.top5storeLinkPath} target='_blank' rel="noreferrer" className={'hover:font-extrabold'}>{ data.top5StoreName}</a></td>
                           <td className={"text-right"}>{((data.top5Sales)/10000).toFixed(0)}</td>
                         </tr>
                       </tbody>
                    </table>    
           </div>
              }
            <div className={'flex flex-col w-1/2 h-3/5 ml-8'}>
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

 export default KakaoResultDataCard;