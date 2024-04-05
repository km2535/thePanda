import axios from "axios";

const ReviewAnalysisInCoupang = async(productId: string,searchPage:number, setData:any) => {
  const page = searchPage / 5;
  let result = {};
  const API_DOMAIN = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/coupang/product/review?productId=${productId}&searchPage=${page}`
  //api 요청
  const startTime  = performance.now();
  await axios.get(API_DOMAIN).then((response) => {
      const endTime = performance.now();
    
    // 요청 소요 시간 계산
    const duration = endTime - startTime;
    console.log(duration)
     result = response.data}
  )
  return result;
}

export default ReviewAnalysisInCoupang;