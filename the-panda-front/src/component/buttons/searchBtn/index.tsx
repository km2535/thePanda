import React from 'react';
import { HiOutlineSearch } from "react-icons/hi";
import { handler } from 'types/buttonsTypes/button-event';

const SearchKeywordButton:React.FC<handler> = ({inputEventHandler}) => {
  return (
    <>
      <div className={'w-2/12 h-full flex justify-center items-center rounded-r-full border-r-1 border-b-1 focus-visible:outline-none cursor-pointer'} onClick={inputEventHandler}>
        <HiOutlineSearch className={'size-9 text-iconsColor'}/>
      </div>
    </>
  )
}
export default SearchKeywordButton;

