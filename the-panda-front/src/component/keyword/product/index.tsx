import parse from 'html-react-parser';
import SalesVolume from '../salesvolume';
import ShoppingInsightSearchCountChart from 'component/chart/searchlinechart';
import ShoppingInsightGanderCount from 'component/chart/genderdougnutchart';
import AgeCountAPI from 'component/chart/ageverticalchart';

const Product = ({ productDetail, productList, productCountExceptAbroad }: any) => {
  
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
              <div>{parse(productDetail?.product_name)}</div>
              <div>{productDetail?.total_qc_cnt}</div>
            </div>
            <div>
                <div>
                  {productDetail?.category1}
                  
                  {productDetail?.category2 ? " > " : ""}
                  {productDetail?.category2}
                  
                  {productDetail?.category3 ? " > " : ""}
                  {productDetail?.category3}
                  
                  {productDetail?.category4 ? " > " : ""}
                  {productDetail?.category4}
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
            {/* 매출 컴포넌트도 JSOUP으로 요청한다. */}
          <SalesVolume/>
        </section>
        {/* 클릭률 컴포넌트는 데이터랩 api에 별도로 요청해서 가져온다. */}
        <section>
            <div >연령대별 클릭률
              <AgeCountAPI/>
          </div>
          <div>요일순</div>
          <div>
            <div>기기별 클릭비율</div>
              <div className={'w-[300px] h-[300px]'}>성별 클릭 비율(%)
              <ShoppingInsightGanderCount productDetail={productDetail} />
                
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
              {productList.map((v: { title: any; },i: number) =>
                <div key={v.title + i}>
                  {parse(v.title)}
                </div>)}
            </div>
          <div>쿠팡 top10</div>
        </section>
      </div>
      }
    </div>
  )
}

export default Product;