import React from 'react';
import { HiOutlineSearch } from "react-icons/hi";

interface handler{
  inputEventHandler: React.MouseEventHandler<HTMLInputElement>,
}
const SearchKeywordButton:React.FC<handler> = ({inputEventHandler}) => {
  return (
    <>
      <div className={'w-2/12 h-full flex justify-center items-center rounded-r-full border-r-2 border-b-4 focus-visible:outline-none cursor-pointer'} onClick={inputEventHandler}>
        <HiOutlineSearch className={'size-9 text-iconsColor'}/>
      </div>
    </>
  )
}
export default SearchKeywordButton;

