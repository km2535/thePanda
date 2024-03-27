import axios from "axios";

const KeywordDetailAPI = (keyword: string,setProductDetail:any) => {
  
  const API_DOMAIN = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/admin/naver/get-up/product-category?keyword=${keyword}`
  //api 요청
  axios.get(API_DOMAIN).then((response) => {
    if (response?.data.length > 0) {
      setProductDetail(response?.data[0]);
    } else {
      // 외부 api를 요청하는 방법으로 변경한다.
      setProductDetail("NoData");
     }
  })
}

export default KeywordDetailAPI;