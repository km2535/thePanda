import axios from "axios";

 const PurchaseCountAPI = (keyword:string ,setDatas:any) => {
   //  TODO: 막힘 다른 방법을 찾아야 함.
   const API_DOMAIN =
     `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/admin/naver/get-crawler/search-product?keyword=${keyword}`;
   axios.get(API_DOMAIN).then((resoponse) =>
     //setDatas(resoponse?.data?.results[0]?.data);
     setDatas(resoponse.data)
    )
   
  } 

 export default PurchaseCountAPI;