export interface InfoTypeForRealTime{
  searchType: string,
  selectedCategory: string,
  dataFilter : filterType
}
export interface categoryType{
  comp_idx : string,
  id : string,
  keyword :string,
  monthly_ave_mobile_cnt : string,
  monthly_ave_pc_cnt: string,
  monthly_mobile_qc_cnt: string,
  monthly_pc_qc_cnt: string,
  total_qc_cnt: string,
  brand: string,
  category1: string,
  category2: string,
  category3: string,
  category4: string,
  is_season: string,
  price: string,
  product_name: string,
  top_product_link: string,
  total_product_count: string,
  update_date: string,
}

export interface filterType{
  searchMin: number,
  searchMax: number,
  copIdxLow : boolean,
  copIdxMiddle : boolean,
  copIdxHigh: boolean,
  brand:boolean
}