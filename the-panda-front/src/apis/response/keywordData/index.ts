import axios from "axios";
import { InfoTypeForRealTime } from "types/propsTypes/props-types";

export const KeywordData = (props:InfoTypeForRealTime,setcmpData:any,setUpdatedate:any) => {
  //옵션에 따라 요청 url을 다르게 함.

  let API_DOMAIN = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/admin/naver`;

  if (props.selectedCategory === "00000000") {
    //모든 데이터에서 브랜드 or 노브랜드 검색
    if (props.dataFilter.brand) {
      API_DOMAIN = API_DOMAIN + "/get-all/product-no-brand?sort=" + props.searchType;
    } else {
      API_DOMAIN = API_DOMAIN + "/get-all/product-detail?sort=" + props.searchType;
    }
  } else {
    //특정 카테고리에서 검색
    if (props.dataFilter.brand) {
      API_DOMAIN =
        `${API_DOMAIN}/get-keyword/product-no-brand?categoryId=${props.selectedCategory}&sort=${props.searchType}`;
    } else {
      API_DOMAIN =
        `${API_DOMAIN}/get-keyword/product-detail?categoryId=${props.selectedCategory}&sort=${props.searchType}`;
    }
  }

  axios.get(API_DOMAIN).then((response) => {  
    setcmpData(response.data.filter((v: { comp_idx: string }) => props.dataFilter.copIdxHigh ? v : (v.comp_idx !== "높음")).filter((v: { comp_idx: string }) => props.dataFilter.copIdxMiddle ? v : (v.comp_idx !== "중간")).filter((v: { comp_idx: string }) => props.dataFilter.copIdxLow ? v : (v.comp_idx !== "낮음")).filter((v: { total_qc_cnt: number }) => Number(v.total_qc_cnt) >= Number(props.dataFilter.searchMin)).reduce((acc: any[], cur: { keyword: any; total_qc_cnt: number; }) => {
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
    response.data[0] && setUpdatedate(response.data[0]?.update_date)
    }).catch((e) => e)
  

}
