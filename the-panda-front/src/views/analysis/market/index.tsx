import React, {useEffect, useState } from 'react';
import { Outlet, useLocation, useNavigate } from 'react-router-dom';

const AnalysisMarket = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const [line, setLine] = useState("after:translate-x-0");
  const moveHandler = (e: any) => {
    const platform = e.target.id;
    if (platform === "coupang") {
      setLine("after:translate-x-0")
    } else if (platform === "naver") {
      setLine("after:translate-x-[160px]")
    }else if (platform === "gmarket") {
      setLine("after:translate-x-[320px]")
    }else if (platform === "kakao") {
      setLine("after:translate-x-[480px]")
    }
    navigate(`${platform}`)
  }
  
  useEffect(() => {
    const path = location.pathname;
    if (path === "/analysis/market/" || path === "/analysis/market") {
      navigate("coupang")
    }
  })  
  return (
       <>
       <div className={'flex w-full'}>
         <div className={'flex w-full justify-center items-center'}>
            <div className={'m-10'}>
             <div className={"bg-coupang w-[80px] h-[80px] bg-cover cursor-pointer hover:shadow-2xl hover:shadow-slate-700 rounded-full transition-all hover:scale-105 after:transition-all after:inline-block after:relative after:top-[65px] after:w-[80px] after:h-[3px] after:shadow-custom "+line} id={"coupang"} onClick={moveHandler}></div>
            </div>
            <div className={'m-10'}>
              <div className={"bg-naver w-[80px] h-[80px] bg-cover cursor-pointer hover:shadow-2xl hover:shadow-slate-700 rounded-full transition-all hover:scale-105"} id={"naver"} onClick={moveHandler}></div>
            </div>
            <div className={'m-10'}>
              <div className={"bg-gmarket w-[80px] h-[80px] bg-cover cursor-pointer hover:shadow-2xl hover:shadow-slate-700 rounded-full transition-all hover:scale-105"} id={"gmarket"} onClick={moveHandler}></div>          
            </div>
            <div className={'m-10'}>
              <div className={"bg-kakao w-[80px] h-[80px] bg-cover cursor-pointer hover:shadow-2xl hover:shadow-slate-700 rounded-full transition-all hover:scale-105"} id={"kakao"} onClick={moveHandler}></div>    
            </div>
         </div>
      </div>
      <Outlet/>
       </>
    )
  } 

 export default AnalysisMarket;