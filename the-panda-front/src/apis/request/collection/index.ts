import axios from "axios";
import { Dispatch, SetStateAction } from "react";
import { categories } from "types/iconsTypes/icons-types"

export const DataCollector = async (setMessage:React.Dispatch<React.SetStateAction<string>>) => {
  
  for (let i = 9; i < categories.length; i++) {
    const category = categories[i].categoryId;
    if (category !== "00000000") {
      try {
        // 데이터 랩 요청
        const response1 = await dataLabRequest(category, setMessage);
        setMessage(`${category}의 데이터 랩을 수집 중...`);
        // 클릭률 요청
        await getClickRate(category, response1.data, setMessage);
        setMessage(`${category}의 키워드 클릭율 수집 중...`);
        
        // 카테고리 데이터 요청
        await getCategory(category, setMessage);
        setMessage(`${category}의 카테고리 수집 중...`);

        // 계절성 상품 요청
        await getIsSeason(category, setMessage);
        setMessage(`${category}의 계절성 상품 수집 중...`);

        // 광고비
        await getClickAd(category, setMessage);
        setMessage(`${category}의 키워드 광고비 수집 중...`);

        // 데이터 백업
        await dataBackUp(category, setMessage);
        setMessage(`${category}의 데이터 백업 완료`);
      } catch (error) {
        // 에러 처리
        console.error(error);
      }
    }
  }
}
//1. 데이터 랩을 요청하는 함수
const dataLabRequest = async (category: string,setMessage: Dispatch<SetStateAction<string>>) => {
  const API_DOMAIN = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/admin/naver/save/top-keyword?category=${category}`
  console.log(API_DOMAIN)
  setMessage(`${category}의 데이터 랩을 수집 중...`)
  
  return await axios.put(API_DOMAIN); 
}
//2. 카테고리를 받아오는 함수 
const getCategory = async (category:string,setMessage: Dispatch<SetStateAction<string>>) => {
  const API_DOMAIN = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/admin/naver/save-data/product-category?_categoryId=${category}`;
  setMessage(`${category}의 카테고리 수집 중..`)
  const result = await axios.put(API_DOMAIN);
  return result;
}
//3. 계절성 상품 요청
const getIsSeason = async (category:string ,setMessage: Dispatch<SetStateAction<string>>) => {
  const API_DOMAIN = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/admin/naver/save-data/product-season`;
  setMessage(`${category}의 계절성 상품 수집 중..`)
  const result = await axios.put(API_DOMAIN);
  return result;
}

//4. 클릭률 요청 -> 에러 키워드 저장
const getClickRate = async (category:string, data:any,setMessage: Dispatch<SetStateAction<string>>) => {
  const API_DOMAIN = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/admin/naver/save-data/product-click`;
  setMessage(`${category}의 키워드 클릭율 수집 중..`)
  const headers = {
    'Content-Type': 'application/json'
  };

  let datas = JSON.stringify({
  category: category,
  list: data
})
  const result = await axios.put(API_DOMAIN, datas,{headers});
  return result;
}
const getClickAd = async (category:string,setMessage: Dispatch<SetStateAction<string>>) => {
  const API_DOMAIN = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/admin/naver/save-data/product-adcost?_categoryId=${category}`;
  setMessage(`${category}의  광고비 수집 중..`)
  const result = await axios.put(API_DOMAIN);
  return result;
}
//5. 데이터 백업 
const dataBackUp = async (category: string,setMessage: Dispatch<SetStateAction<string>>) => {
  const API_DOMAIN = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/admin/naver/back-up/product-category?_categoryId=${category}`
  setMessage(`${category}의 데이터 백업 중...`)
  return await axios.put(API_DOMAIN); 
}