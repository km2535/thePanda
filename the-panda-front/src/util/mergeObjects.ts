import { categoryType } from "types/propsTypes/props-types";


export function mergeObjects(categories: categoryType[], cmpIdx: categoryType[]): categoryType[] {
  const mergedArray: categoryType[] = [];

  for (const cate of categories) {
    for (const cmp of cmpIdx) {
      if (cate.keyword === cmp.keyword) {
        const mergedItem: categoryType = {
          id: cmp.id,
          keyword: cate.keyword,
          comp_idx: cmp.comp_idx,
          monthly_ave_mobile_cnt:cmp.monthly_ave_mobile_cnt,
          monthly_ave_pc_cnt: cmp.monthly_ave_pc_cnt,
          monthly_mobile_qc_cnt: cmp.monthly_mobile_qc_cnt,
          monthly_pc_qc_cnt: cmp.monthly_pc_qc_cnt,
          total_qc_cnt: cmp.total_qc_cnt,
          brand: cate.brand,
          category1: cate.category1,
          category2: cate.category2,
          category3: cate.category3,
          category4: cate.category4,
          is_season: cate.is_season,
          price: cate.price,
          product_name: cate.product_name,
          top_product_link: cate.top_product_link,
          total_product_count: cate.total_product_count,
          update_date: cmp.update_date
        };
        mergedArray.push(mergedItem);
      }
    }
  }
  mergedArray.sort((a, b) => Number( b.total_qc_cnt) - Number(a.total_qc_cnt));

  return mergedArray;
}
