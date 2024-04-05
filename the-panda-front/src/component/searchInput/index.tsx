import SelectTagForSearch from 'component/buttons/selectBtn';
import SearchInputBasic from './input';

interface handler{
  inputEvent: React.MouseEventHandler<HTMLInputElement>,
  changeEvent: React.ChangeEventHandler<HTMLInputElement> | React.ChangeEventHandler<HTMLSelectElement>,
  inputRef: any
}
//change핸들러와 버튼 핸들러를 전달 받는다.
const SearchInput: React.FC<handler> = ({ inputEvent, changeEvent, inputRef }) => {
    return (
    <div className='flex justify-between h-[80px] align-middle '>
      <SelectTagForSearch changeEventHandler={changeEvent as React.ChangeEventHandler<HTMLSelectElement>}/>
      <SearchInputBasic changeEvent={changeEvent} inputEvent={inputEvent} inputRef={inputRef} placeholder="상품을검색하세요."/>
    </div>
  )
} 

export default SearchInput;

