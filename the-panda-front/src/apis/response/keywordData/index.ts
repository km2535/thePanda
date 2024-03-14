import axios from "axios";
import { InfoTypeForRealTime } from "types/propsTypes/props-types";

export const KeywordData = (props:InfoTypeForRealTime,setcmpData:any,setUpdatedate:any) => {
  //옵션에 따라 요청 url을 다르게 함.
  //필요 시 response.data를 filter로 수정
  const API_DOMAIN = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/keyword/naver/top100/${props.selectedCategory}`
  axios.get(API_DOMAIN).then((response) => {
    setcmpData(response.data.filter((v: { brand: string; }) => props.dataFilter.brand ? v.brand === "" : v.brand !== "").filter((v: { comp_idx: string }) => props.dataFilter.copIdxHigh ? v : (v.comp_idx !== "높음")).filter((v: { comp_idx: string }) => props.dataFilter.copIdxMiddle ? v : (v.comp_idx !== "중간")).filter((v: { comp_idx: string }) => props.dataFilter.copIdxLow ? v : (v.comp_idx !== "낮음")).filter((v:{total_qc_cnt:number})=> Number(v.total_qc_cnt) >= Number(props.dataFilter.searchMin)).reduce((acc: any[], cur: { keyword: any; total_qc_cnt: number; }) => {
  const existingData = acc.find((data) => data.keyword === cur.keyword);
  if (!existingData || cur.total_qc_cnt > existingData.total_qc_cnt) {
    if (existingData) {
      acc.splice(acc.indexOf(existingData), 1);
    }
    acc.push(cur);
  }
  return acc;
}, []).slice(0,100)
    )
    response.data[0] && setUpdatedate(response.data[0]?.updatedate)
  }).catch((e) => e)
}
