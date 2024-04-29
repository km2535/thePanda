import axios from "axios";

export const getCoupangProductData = async(keywords:string) => {
  const API_URL = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/coupang/multi-product/info?keywords=${keywords}`;
  return await axios.get(API_URL).then((response)=>response.data)
}

export const getGmarketProductData = async (keywords: string) => {
  const API_URL = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/gmarket/product/detail?keywords=${keywords}`;
  return await axios.get(API_URL).then((response)=>response.data)
}

export const getKakaoProductData = async (keywords: string) => {
  const API_URL = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/kakao/product/detail?keywords=${keywords}`;
  return await axios.get(API_URL).then((response)=>response.data)
}

