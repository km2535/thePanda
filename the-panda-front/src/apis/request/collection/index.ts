import axios from "axios";
import { Dispatch, SetStateAction } from "react";
import { categories } from "types/iconsTypes/icons-types"

export const DataCollector = async (setMessage:React.Dispatch<React.SetStateAction<string>>) => {
  //api 요청 로직...
  // for (let i = 0; i < categories.length; i++) {
  //   try {  
  //     const category = categories[i].categoryId;
  //     if (categories[i].categoryId !== "00000000") {
  //       dataLabRequest(category, setMessage)
  //       .then((response) => getCategory(category, response.data, setMessage)
  //       .then(() => getIsSeason(category, response.data, setMessage))
  //       .then(() => getClickRate(category, response.data, setMessage)
  //       .then((response) => getClickRate(category, response.data, setMessage)
  //       .then((response) => getClickRate(category, response.data, setMessage)))))
  //       .then(()=> dataBackUp(category,setMessage)).then(()=>setMessage(`${category} 완료`))
  //     }
  //   } catch (error) {
      
  //   }
  // }
  
  //getCategory("50000007", ["캠ㅍ핑의자"],setMessage)
  //포스트 맨으로 검사
  for (let i = 0; i < categories.length; i++) {
    const category = categories[i].categoryId;
    if (category !== "00000000") {
      try {
        // 데이터 랩 요청
        const response1 = await dataLabRequest(category, setMessage);
        setMessage(`${category}의 데이터 랩을 수집 중...`);

        // 카테고리 데이터 요청
        await getCategory(category, response1.data, setMessage);
        setMessage(`${category}의 카테고리 수집 중...`);

        // 계절성 상품 요청
        await getIsSeason(category, response1.data, setMessage);
        setMessage(`${category}의 계절성 상품 수집 중...`);

        // 클릭률 요청
        await getClickRate(category, response1.data, setMessage);
        setMessage(`${category}의 키워드 클릭율 수집 중...`);

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
  const API_DOMAIN = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/admin/datalab/get-keyword?category=${category}`
  setMessage(`${category}의 데이터 랩을 수집 중...`)
  
  return await axios.get(API_DOMAIN); 
}
//2. 카테고리를 받아오는 함수 
const getCategory = async (category:string, data:any,setMessage: Dispatch<SetStateAction<string>>) => {
  const API_DOMAIN = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/admin/get-data/product-category`;
  setMessage(`${category}의 카테고리 수집 중..`)
  const formdata = new FormData();
  formdata.append('category', category);
  formdata.append('list', data);
  const result = await axios.post(API_DOMAIN, formdata);
  return result;
}
//3. 계절성 상품 요청
const getIsSeason = async (category:string, data:any,setMessage: Dispatch<SetStateAction<string>>) => {
  const API_DOMAIN = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/admin/get-data/product-season`;
  setMessage(`${category}의 계절성 상품 수집 중..`)
  const formdata = new FormData();
  formdata.append('category', category);
  formdata.append('list', data);
  const result = await axios.post(API_DOMAIN, formdata);
  return result;
}

//4. 클릭률 요청 -> 에러 키워드 저장
const getClickRate = async (category:string, data:any,setMessage: Dispatch<SetStateAction<string>>) => {
  const API_DOMAIN = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/admin/get-data/product-click`;
  setMessage(`${category}의 키워드 클릭율 수집 중..`)
  const formdata = new FormData();
  formdata.append('category', category);
  formdata.append('list', data);
  const result = await axios.post(API_DOMAIN, formdata);
  return result;
}
//5. 데이터 백업 
const dataBackUp = async (category: string,setMessage: Dispatch<SetStateAction<string>>) => {
  const API_DOMAIN = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/admin/data/backup?category=${category}`
  setMessage(`${category}의 데이터 랩을 수집 중...`)
  return await axios.get(API_DOMAIN); 
}