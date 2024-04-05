import axios from "axios";

//네이버 
 const ProductTrackerRankAPI = async (keywords:string, midNumber:string) => {
   const API_DOMAIN =
     `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/admin/naver/naver-tracking/rank?keywords=${keywords}&midNumber=${midNumber}`;
   try {
    const response = await axios.get(API_DOMAIN);
    return response.data;
  } catch (error) {
    console.log(error);
    return null; // 에러가 발생하면 null을 반환하도록 수정
  }
  } 

export default ProductTrackerRankAPI;
 


//쿠팡 
export const ProductTrackerRankCoupangAPI = async (keywords:string, productId:string, sorted:string) => {
  const API_DOMAIN =
     `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/coupang/coupang-tracking/rank?keywords=${keywords}&productId=${productId}&sorted=${sorted}`;
   try {
    const response = await axios.get(API_DOMAIN);
    return response.data;
  } catch (error) {
    console.log(error);
    return null; // 에러가 발생하면 null을 반환하도록 수정
  }
}