import SearchKeywordButton from 'component/buttons/searchBtn';
import SelectTagForSearch from 'component/buttons/selectBtn';

interface handler{
  inputEvent: React.MouseEventHandler<HTMLInputElement>,
  changeEvent: React.ChangeEventHandler<HTMLInputElement> | React.ChangeEventHandler<HTMLSelectElement>,
  inputRef: any
}
//change핸들러와 버튼 핸들러를 전달 받는다.
const SearchInput:React.FC<handler> = ({inputEvent, changeEvent,inputRef}) => {
  return (
    <div className='flex justify-between h-[80px] align-middle '>
      <SelectTagForSearch changeEventHandler={changeEvent as React.ChangeEventHandler<HTMLSelectElement>}/>
      <input ref={inputRef} placeholder='상품을 검색하세요' id='keyword' className={'w-full border-b-1 focus-visible:outline-none text-center h-full text-2xl focus:placeholder:invisible'} type="text" onChange={changeEvent as React.ChangeEventHandler<HTMLInputElement>} />
      <SearchKeywordButton inputEventHandler={inputEvent}/>
    </div>
  )
}

export default SearchInput;

