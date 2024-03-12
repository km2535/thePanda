import axios from "axios";
import { InfoTypeForRealTime } from "types/propsTypes/props-types";

export const KeywordData = (props:InfoTypeForRealTime,setcmpData:any,setCategoriesData: any,setUpdatedate:any) => {
  const API_DOMAIN_COMPIDX = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/keyword/naver/trend-keyword/${props.selectedCategory}`;
  const API_DOMAIN_CATEGORY = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/keyword/naver/trend-keyword/base-${props.selectedCategory}`;
  axios.get(API_DOMAIN_COMPIDX).then((response) => 
   {setcmpData(response.data)
    response.data[0] && setUpdatedate(response.data[0]?.update_date)
  }
  ).then(() =>
  axios.get(API_DOMAIN_CATEGORY).then((response) => setCategoriesData(response.data))
  )
}