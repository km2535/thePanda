import axios from "axios";


export const optimizationKeyword = async (keyword: string, type: string) => {
  const BASE_URL = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/extraction/product-name/`
  return await axios.get(`${BASE_URL}${type}?keyword=${keyword}`).then((res)=>{return res.data})
}