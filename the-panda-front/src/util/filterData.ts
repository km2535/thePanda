import { categoryType, filterType } from "types/propsTypes/props-types";

const filterData = (newData:categoryType[], filter:filterType) => {
  return newData.filter((item) => {
    const totalQcCnt = parseInt(item.total_qc_cnt);
    const meetsSearchRange = totalQcCnt >= filter.searchMin && totalQcCnt <= filter.searchMax;
    const meetsBrand = !(filter.brand && item.brand !== "");
    const meetsCopIdxLow = (item.comp_idx === "낮음") && filter.copIdxLow;
    const meetsCopIdxMiddle = (item.comp_idx === "중간") && filter.copIdxMiddle;
    const meetsCopIdxHigh = (item.comp_idx === "높음")&&filter.copIdxHigh;
    return meetsSearchRange && (meetsCopIdxLow || meetsCopIdxMiddle || meetsCopIdxHigh) && meetsBrand;
  })
}

export default filterData;