import React from 'react';
import { handlerWithButtonName } from 'types/buttonsTypes/button-event';


const FilterButton:React.FC<handlerWithButtonName> = ({inputEventHandler, buttonName}) => {
  return (
    <>
      <div className='cursor-pointer bg-buttonColor p-2 text-center hover:bg-brandHoverFontColor rounded-xl mt-2 mb-2' onSubmit={inputEventHandler}>
        <button type='submit' className={'text-fontwhiteColor'} value={buttonName }/>
      </div>
    </>
  )
}

export default FilterButton;