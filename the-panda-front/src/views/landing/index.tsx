import { useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import SearchInMainPage from 'apis/request/search/searchInMainPage';
import NaviButtonForDataTranslation from 'component/buttons/navDataTranslateBtn';
import SearchInput from 'component/searchInput';
import ButtonInIcons from 'component/buttons/iconsBtn';
import { categories } from 'types/iconsTypes/icons-types';
import InfoBox from 'component/infoBox';
import { buttontitle } from 'types/buttonsTypes/button-types';
import MainFilter from 'component/filter/mainFilter';
import { filterType } from 'types/propsTypes/props-types';

export default function Landing() {
  const navigate = useNavigate();
  const inputRef = useRef<any>();
  const [searchKeyword, setSearchKeyword] = useState({ searchKind: "analysis", keyword: "" });
  const [searchType, setSearchType] = useState("trend-keyword");
  const [searchTypeForRealTime, setSearchTypeForRealTime] = useState(buttontitle);
  const [categoryiesCopy, setCategoryiesCopy] = useState(categories);
  const [selectedCategory, setSelectedCategory] = useState("00000000");
  const [filterInfo, setFilterInfo] = useState<filterType>({
      searchMax: 3000000,
      searchMin: 100,
      copIdxHigh: true,
      copIdxMiddle: true,
      copIdxLow: true,
      brand: false});
  const mainLogo = () => {
    navigate("/");
  };

  const inputHandler = () => {
    if (inputRef.current.value === "") {
      inputRef.current.focus();
    } else {
      SearchInMainPage(searchKeyword);
      inputRef.current.value = "";
    }
  };

  const filterHandler = (e: any) => {
    e.preventDefault();
    setFilterInfo({
      searchMax: e.target.searchMax.value,
      searchMin: e.target.searchMin.value,
      copIdxHigh: e.target.cmpIdxHigh.checked,
      copIdxMiddle: e.target.cmpIdxMiddle.checked,
      copIdxLow: e.target.cmpIdxLow.checked,
      brand: e.target.brand.checked
    })
  }
  
  const changeHandler = (e:any) => {
    const id = e.target.id;
    if (id === "searchKind") {
      setSearchKeyword(prev => ({ ...prev, searchKind: e.target.value }));
    }
    if (id === "keyword") {
      setSearchKeyword(prev => ({ ...prev, keyword: e.target.value }));
    }
  };

  const setSearchTypeForRealTimeByCall = (e:any) => {
    const typeName = e.target.id;
    setSearchTypeForRealTime(prev => {
      return prev.map(item => ({
        ...item,
        isActive: item.typeName === typeName
      }));
    });
  };
  const setCategoryTypeForRealTimeByCall = (e:any) => {
    const categories = e.target.id;
    setSelectedCategory(categories);
    setCategoryiesCopy(prev => {
      return prev.map(item => ({
        ...item,
        isActive: item.categoryId === categories
      }));
    });
  };

  const realTimeSearchHandler = (e:any) => {
    setSearchTypeForRealTimeByCall(e);
  };
  return (
    <div className="w-full m-auto mt-10 overflow-hidden">
      <div className="w-full h-full overflow-hidden">
        <section className="w-full h-[200px] mt-15">
          <div className="bg-logo w-[800px] m-auto h-[200px] bg-no-repeat bg-cover bg-center cursor-pointer" onClick={mainLogo}></div>
        </section>
        <section className={'mt-10'}>
          <div className={'shadow-xl rounded-full'}>
            <SearchInput inputRef={inputRef} changeEvent={changeHandler} inputEvent={inputHandler} />
          </div>
        </section>
        <section className={'mt-10'}>
          <div className={'flex w-3/5 justify-between items-center mr-auto ml-auto mt-5 mb-5'}>
          {searchTypeForRealTime.map((v, i) => (
            <NaviButtonForDataTranslation
            setType={setSearchType}
              key={v.typeName + i}
              realTimeSearchHandler={realTimeSearchHandler}
              buttonName={v.buttonName}
              isActive={v.isActive}
              typeName={v.typeName}
              />
              ))}
          </div>
        </section>
        <section className={'mt-10'}>
          {/* 카테고리 전환 */}
          <div className={'flex justify-between '}>
          {
            categoryiesCopy.map((v,i) =>
              <ButtonInIcons key={v.categoryId+i} setCategoryTypeForRealTimeByCall={setCategoryTypeForRealTimeByCall} isActive={v.isActive} category={v.category} iconImagePath={v.iconImagePath} categoryId={v.categoryId} />
            )
          }
          </div>
        </section>
        <section className={'mt-10 flex'}>
          <div className={'w-3/4 mr-5 mb-5'}>
            {/* 실시간 정보 창 component */}
            <InfoBox dataFilter={filterInfo} searchType={searchType} selectedCategory={selectedCategory}  />
          </div>
          <div className='w-2/5 mr-2'>
            <div>
              {/* 필터 정보 창 component */}
              <MainFilter inputEventHandler={filterHandler} />
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
