import SearchKeywordButton from 'component/buttons/searchBtn';

interface handler{
  inputEvent: React.MouseEventHandler<HTMLInputElement>,
  changeEvent: React.ChangeEventHandler<HTMLInputElement> | React.ChangeEventHandler<HTMLSelectElement>,
  inputRef: any,
  placeholder:string
}
//change핸들러와 버튼 핸들러를 전달 받는다.
const SearchInputBasic: React.FC<handler> = ({ inputEvent, changeEvent, inputRef, placeholder}) => {
  const enterDown = (e:any) => {
    if (e.keyCode === 13) {
      inputEvent(e);
    }
  }
  return (
    <>
      <input ref={inputRef} placeholder={placeholder} id='keyword' className={'w-full border-b-1 focus-visible:outline-none text-center h-full text-2xl focus:placeholder:invisible'} type="text" onChange={changeEvent as React.ChangeEventHandler<HTMLInputElement>}  onKeyDown={enterDown}/>
      <SearchKeywordButton inputEventHandler={inputEvent}/>
    </>
  )
}

export default SearchInputBasic;

