import axios from "axios";

 const GenderCountAPI = (keyword:string, categoryId:string, setDatas:any) => {
   const API_DOMAIN =
     `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/admin/naver/get-datalab/search-gender?keyword=${keyword}&categoryId=${categoryId}`;
   axios.get(API_DOMAIN).then((resoponse) => {
     setDatas(resoponse?.data?.results[0]?.data);
    })
   
  } 

 export default GenderCountAPI;