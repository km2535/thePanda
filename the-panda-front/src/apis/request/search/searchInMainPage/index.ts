
//발굴 /keyword/search
//분석 /keyword/analysis
//연관키워드 /keyword/related
export interface searchDTO {
  searchKind: string,
  keyword: string
}
const SearchInMainPage = (search : searchDTO) => {
  //분석페이지 이동 후 해당 키워드를 이용해서 api를 요청한다.
  console.log(search.keyword);
  console.log(search.searchKind);
  //요청 api는 추후에 작성
}

export default SearchInMainPage;