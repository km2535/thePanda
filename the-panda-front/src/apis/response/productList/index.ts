import axios from "axios";

const ProductListAPI = (keyword: string,setProductList:any) => {
  
  const API_DOMAIN = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/admin/naver/get-up/product-List?keyword=${keyword}`
  //api 요청
  axios.get(API_DOMAIN).then((response) => setProductList(response.data))
}

export default ProductListAPI;