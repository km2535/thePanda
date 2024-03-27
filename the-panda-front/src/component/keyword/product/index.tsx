import parse from 'html-react-parser';

const Product = ({ productDetail, productList }: any) => {
  
  return (
    <div className={'w-full flex'}>
      {productDetail === "NoData" ? "존재하지 않는 키워드 입니다." :
      <div>
        <section>
          <div>이미지</div>
          <div>
            <div>
              <div>{productDetail?.keyword}</div>
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
            {/* 데이터랩 카테고리로 해외상품을 제외시켜서 계산한다. */}
            <div>{ productDetail?.total_product_count}</div>
            <div>해외상품수</div>
            <div>해외상품비율</div>
          </div>
          <div>
            {/* 매출 컴포넌트도 JSOUP으로 요청한다. */}
            <div>평균판매가(6개월)</div>
            <div>6개월 매출</div>
            <div>6개월 판매량</div>
          </div>
        </section>
        {/* 클릭률 컴포넌트는 데이터랩 api에 별도로 요청해서 가져온다. */}
        <section>
          <div>연령대별 클릭률</div>
          <div>요일순</div>
          <div>
            <div>기기별 클릭비율
              <div>{productDetail?.monthly_ave_mobile_cnt}</div>
              <div>{productDetail?.monthly_ave_pc_cnt}</div>
             </div>
            <div>성별 클릭 비율</div>
          </div>
        </section>
        <section>
          <div>키워드별 광고 운용결과</div>
        </section>
        <section>
           <div>차트(1개월 / 1년 / 3년)</div>
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