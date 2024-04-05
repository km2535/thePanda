import parse from 'html-react-parser';
import ShoppingInsightSearchCountChart from 'component/chart/search';
import ShoppingInsightAgeCountChart from 'component/chart/age';
import ShoppingInsightGenderCountChart from 'component/chart/gender';
import ShoppingInsightDeviceCountChart from 'component/chart/device';
import ShoppingInsightWeekendsCountChart from 'component/chart/week';
import ProductListCoupang from './coupangList';
import { useEffect, useState } from 'react';

const Product = ({ productDetail, productList, productCountExceptAbroad }: any) => {
  const [categoryPercent, setCartegoryPercent] = useState({category2 : 0,category3 : 0,category4 : 0, });
  useEffect(() => {
    let category2 = 0;
    let category3 = 0;
    let category4 = 0;
    
    productList.map((item: any) => {
      category2 =item?.category2 && item?.category2 === productDetail?.category2 ? category2+1 :category2 
      category3 =item?.category3 && item?.category3 === productDetail?.category3  ? category3+1 :category3 
      category4 =item?.category4 && item?.category4 === productDetail?.category4 ? category4 + 1 : category4 
      return 0;
    })

    setCartegoryPercent({
      category2: parseFloat(((category2 / productList?.length) * 100).toFixed(2)),
      category3: parseFloat(((category3 / productList?.length)* 100).toFixed(2)),
      category4: parseFloat(((category4 / productList?.length)* 100).toFixed(2))
    })
  }, [productDetail?.category2, productDetail?.category3, productDetail?.category4, productList])
  return (
    <div className={'w-full flex'}>
      {productDetail === "NoData" ? "존재하지 않는 키워드 입니다." :
      <div>
        <section>
            <div>
              <img src={productDetail?.top_product_link} alt={productDetail?.keyword}/>
            </div>
          <div>
            <div>
              <div>{productDetail?.keyword}</div>
              <div>{productDetail && parse(productDetail?.product_name)}</div>
              <div>{productDetail?.total_qc_cnt}</div>
            </div>
            <div>
                <div>
                  {productDetail?.category1}
                  
                  {productDetail?.category2 ? " > " : ""}
                  {productDetail?.category2}
                  {productDetail?.category2 ? (!(productDetail?.category3) ? `(${categoryPercent.category2 }%)`  : "" ): ""}
                  
                  {productDetail?.category3 ? " > " : ""}
                  {productDetail?.category3}
                  {productDetail?.category3 ? (!(productDetail?.category4) ? `(${categoryPercent.category3}%)`  : "" ): ""}
                  
                  {productDetail?.category4 ? `" > ${productDetail?.category4} (${categoryPercent.category4})"` :""}
                
              </div>
              <div>{productDetail?.comp_idx}</div>
            </div>
          </div>
        </section>
        <section>
          <div>
            <div>{ productDetail?.total_product_count}</div>
              <div>해외상품수 : { productDetail?.total_product_count - productCountExceptAbroad}</div>
              <div>해외상품비율 : { ((productDetail?.total_product_count - productCountExceptAbroad) /productDetail?.total_product_count * 100 ).toFixed(2)}%</div>
          </div>
            {/*  매출 컴포넌트 셀레니움 막힘*/} 
          {/* <SalesVolume /> */}
        </section>
        {/* 클릭률 컴포넌트는 데이터랩 api에 별도로 요청해서 가져온다. */}
        <section>
          <div>요일순
            <ShoppingInsightWeekendsCountChart productDetail={productDetail}/>  
          </div>
          <div className={'flex '}>
            <div className={'w-[380px] h-[380px]'}>기기별 클릭비율(%)
              <ShoppingInsightDeviceCountChart productDetail={productDetail}/>
            </div>
            <div className={'w-[400px] h-[400px]'}>연령대별 클릭률(%)
              <ShoppingInsightAgeCountChart productDetail={productDetail}/>
            </div>
            <div className={'w-[380px] h-[380px]'}>성별 클릭 비율(%)
              <ShoppingInsightGenderCountChart productDetail={productDetail} />      
            </div>
          </div>
        </section>
        <section>
            <div>키워드 최저 광고비 (클릭당): {productDetail?.ad_cost}원</div>
        </section>
        <section>
            <div>차트(1개월 / 1년 / 3년)
              <ShoppingInsightSearchCountChart productDetail={productDetail} />
           </div>
        </section>
        <section>
            <div>네이버 top10 
              {productList.map((v: { title: any; }, i: number) =>
                i<10?
                <div key={v.title + i}>
                  {parse(v.title)}
                </div>: "") }
            </div>
            <div>쿠팡 top10 
              <ProductListCoupang/>
          </div>
        </section>
      </div>
      }
    </div>
  )
}

export default Product;