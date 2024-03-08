import SearchInMainPage, { searchDTO } from 'apis/request/search/searchInMainPage';
import SearchInput from 'component/searchInput';
import {  useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';

export default function Landing() {
  const navigate = useNavigate();
  const inputRef = useRef<any>();
  const [searchKeyword, setSearchKeyword] = useState<searchDTO>({ searchKind: "search", keyword: "" });
  const mainLogo = () => {
    navigate("/");
  }
  const inputHandler = () => {
    // input버튼로 전달하는 함수, 검색 결과를 이동시킨다. 
    if (inputRef.current.value === "") {
      inputRef.current.focus();
    } else {
      SearchInMainPage(searchKeyword);
      inputRef.current.value = "";
    } 
  }
  
  const changeHandler = (e: any) => {
    const id = e.target.id;
    if (id === "searchKind") {
      setSearchKeyword((prev) => ({...prev, searchKind : e.target.value}));
    }
    if (id === "keyword") {
      setSearchKeyword((prev) => ({...prev, keyword : e.target.value}));
    }
  }


  return (
    <div className={"w-8/12 m-auto mt-10 overflow-hidden"}>
      <div className={"w-full h-fulloverflow-hidden"}>
        <section className={"w-full h-[200px] mt-20"}>
          <div className={"bg-logo w-[800px] m-auto h-[200px] bg-no-repeat bg-cover bg-center cursor-pointer"} onClick={mainLogo}></div>
        </section>
        <section>
          <div>
            {/* 검색 창 component */}
            <SearchInput inputRef={inputRef} changeEvent={changeHandler} inputEvent={inputHandler}/>
          </div>
        </section>
        <section>
          {/* 실시간 정보 바꾸는 경로 */}
        </section>
        <section>
          {/* 카테고리 전환 */}
        </section>
        <section>
          <div>
            {/* 실시간 정보 창 component */}
          </div>
          <div>
            <div>
              { /* 필터 정보 창 component */}
            </div>
            <div>
              {/* 광고 정보 창 component */}
            </div>
          </div>
        </section>
      </div>
    </div>
  );
}

