import React from 'react';

interface handler{
  changeEventHandler:  React.ChangeEventHandler<HTMLSelectElement>
}
const SelectTagForSearch:React.FC<handler> = ({changeEventHandler}) => {
  return (
    <>
      <div className={'w-2/12 h-full'}>
          <select name="" id="searchKind" className='cursor-pointer focus-visible:outline-none w-full h-full rounded-l-full border-l-1 border-b-1 text-center text-xl' onChange={changeEventHandler}>
            <option value="analysis">분석</option>
            <option value="search" className={'cursor-pointer'}>발굴</option>
            <option value="related">연관키워드</option>
          </select>
      </div>
    </>
  )
}
export default SelectTagForSearch;

