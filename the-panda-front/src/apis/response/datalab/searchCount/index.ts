import axios from "axios";

 const SearchCountAPI = (keyword:string, categoryId:string, ago:number, type:string ,setDatas:any) => {
   const API_DOMAIN =
     `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/admin/naver/get-datalab/search-result?keyword=${keyword}&categoryId=${categoryId}&ago=${ago}&type=${type}`;
   axios.get(API_DOMAIN).then((resoponse) => {
     setDatas(resoponse?.data?.results[0]?.data);
    })
   
  } 

 export default SearchCountAPI;