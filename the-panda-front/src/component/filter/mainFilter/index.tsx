import React from 'react';
import { MdFilterAlt } from "react-icons/md";
import {  handlerForm } from 'types/buttonsTypes/button-event';


const MainFilter:React.FC<handlerForm> = ({inputEventHandler}) => {
  return (
    <form onSubmit={inputEventHandler} className='bg-backgroundColor p-2 rounded-xl shadow-xl'>
      <div className={'text-iconsColor flex flex-row items-center justify-end '}><MdFilterAlt/> 검색 필터 설정하기</div>
      <div>
        <div className={'flex flex-row w-full mt-4'}>
          <div className={'w-1/5 ml-3 text-iconsColor '}>검색량</div>
          <div className='w-2/5 pr-2 pl-2'><input name='searchMin' placeholder='최소값' className={'w-full focus-visible:outline-none border-[1px] rounded-lg border-solid border-neutral-500 focus:placeholder:invisible text-center'} type="number" min={100} step={100} defaultValue={100}/></div>
          <div className={'w-1/8'}>-</div>
          <div className='w-2/5 pr-2 pl-2'><input name='searchMax' placeholder='최대값' className={'w-full border-[1px] rounded-lg border-solid border-neutral-500 focus-visible:outline-none focus:placeholder:invisible text-center'} type="number" min={1000} step={100} defaultValue={1000}/></div>
        </div>
        <div className={'flex flex-row w-full mt-4'}>
          <div className={'w-1/5 ml-3 text-iconsColor '}>경쟁강도</div>
          <div className='w-4/5 pr-2 pl-2 flex justify-evenly'>
            <div>
              <label htmlFor="low">낮음</label><input type="checkbox"defaultChecked={true} name="cmpIdxLow" id="low" />
            </div>
            <div>
              <label htmlFor="middle">중간</label><input type="checkbox" defaultChecked={true} name="cmpIdxMiddle" id="middle" />
            </div>
            <div>
              <label htmlFor="high">높음</label><input type="checkbox" defaultChecked={true} name="cmpIdxHigh" id="high" />
            </div>
          </div>
          
        </div>
        <div  className={'w-1/3 flex ml-3 text-iconsColor mt-4'}>
          <div>브랜드 제거</div>
          <div className={'ml-2'}>
            <input type="checkbox" name="brand" id="brand" defaultChecked={true} />
          </div>
        </div>
        <div>
          <div className='cursor-pointer bg-buttonColor p-2 text-center hover:bg-brandHoverFontColor rounded-xl mt-2 mb-2'>
          <button type='submit' className={'text-fontwhiteColor'}>검색필터 설정하기</button>
        </div>
        </div>
      </div>
    </form>
  );
}

export default MainFilter;
