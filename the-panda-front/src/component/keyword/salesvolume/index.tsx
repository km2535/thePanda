import PurchaseCountAPI from "apis/response/datalab/purchaseCount";
import { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom";

const SalesVolume = () => {
    const [searchParams] = useSearchParams();
  //console.log(productDetail)  
  const [datas, setDatas] = useState({
    totalRevenue : 0,
    totalSalesCount : 0,
     averagePrice :  0
  });
  useEffect(() => {
    const keyword = searchParams.get("keyword")
    if (keyword) {
      
        PurchaseCountAPI(keyword,setDatas);
      
    }
  }, [searchParams])

  return (
    <div>
      <div>평균판매가(6개월) : {datas?.averagePrice}원</div>
      <div>6개월 매출  : {datas?.totalRevenue}원</div>
      <div>6개월 판매량 : {datas?.totalSalesCount}개</div>
    </div>
  )  
}

export default SalesVolume;
