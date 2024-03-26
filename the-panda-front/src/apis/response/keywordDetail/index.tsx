import axios from "axios";

const KeywordDetailAPI = (keyword: string,setProductDetail:any) => {
  
  const API_DOMAIN = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/amin/naver/get-up/product-category?keyword=${keyword}`
  //api 요청
  axios.get(API_DOMAIN).then((response) => setProductDetail(response.data[0]))
}

export default KeywordDetailAPI;