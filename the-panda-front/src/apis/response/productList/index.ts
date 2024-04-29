import axios from "axios";

//상품 리스트
const ProductListAPI = (keyword: string,setProductList:any) => {
  
  const API_DOMAIN = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/admin/naver/get-up/product-List?keyword=${keyword}`
  //api 요청
  axios.get(API_DOMAIN).then((response) => setProductList(response.data))
}
export default ProductListAPI;

// 해외 상품을 제외한 상품의 갯수
export const ProdcutExceptAbroadAPI = (keyword:string, setProductCountExceptAbroad:any) => {
  const API_DOMAIN = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/admin/naver/get-product/except-abroad?keyword=${keyword}`
  axios.get(API_DOMAIN).then((response) => setProductCountExceptAbroad(response.data))
}


//쿠팡 상품 리스트 
export const ProductListCoupangAPI = (keyword: string, setProductList: any) => {
 setProductList([])
  const API_DOMAIN = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/extraction/product-name/coupang?keyword=${keyword}`
  //api 요청
  axios.get(API_DOMAIN).then((response) => setProductList(response.data))
}
