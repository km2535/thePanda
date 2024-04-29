import DepthNavBtn from 'component/buttons/depthNavBtn';
import MenuBtn from 'component/buttons/navbarButton';
import { useAuth } from 'component/context/AuthContext';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

interface MenuProps { 
  mainMenuList: { [key: string]: { [key: string]: string } }
}

const NavBar: React.FC<MenuProps> = ({ mainMenuList }) => {
  const { userInfo, logOutHandler } = useAuth();

 const [isToggle, setIsToggle] = useState<boolean>(false);
  const navigate = useNavigate();
  const menuHandler = () => {
    setIsToggle(() => !isToggle);
  }
  const logoClick = () => {
    navigate("/")
  }
  
  return (
     <div className={isToggle? "w-full z-50 text-brandFontColor pt-4 h-[100px] border-b-zinc-500 shadow-sm transition-all sticky top-0 bg-slate-50":"  w-full text-brandFontColor pt-4 h-[60px]  border-b-zinc-500 shadow-sm transition-all sticky top-0 bg-slate-50 z-50"}>
      <div className="container mx-auto flex justify-between items-center m-auto max-w-[1450px]">
        <div className='w-1/6 h-full'>
          <div className="bg-center bg-logo w-full h-10 bg-no-repeat cursor-pointer" onClick={logoClick}></div>
        </div>
        <div className="flex w-1/2 space-x-8 flex-wrap	">
          {
            Object.entries(mainMenuList).map((v, i) =>
              Object.keys(v[1]).length > 2 ? 
                <DepthNavBtn mainMenuList={{ [v[0]]: v[1] }} key={i} menuHandler={menuHandler} />
               :
            <MenuBtn menuHandler={menuHandler}  key={i} isChild={false} buttonName={ Object.keys(v[1]).toString()} buttonPath={Object.values(v[1]).toString()} />
          )}
         
        </div>
        
        <div className='flex w-1/4 text-center justify-center'>
          <div>
          <MenuBtn menuHandler isChild={false} buttonName='사용가이드' buttonPath='/guide'/>
          </div>
          <div>
          <MenuBtn menuHandler isChild={false}  buttonName='멤버십' buttonPath='/membership'/>
          </div>
          <div>
            {userInfo !== null ? <>
              {userInfo?.username}님 <button onClick={logOutHandler}>로그아웃</button>
            </>
            :
            <MenuBtn menuHandler isChild={false}  buttonName='로그인' buttonPath='/auth/sign-up'/>
            }
          </div>
        </div>    
      </div>
      <div className={"container mx-auto justify-between flex items-center max-w-[1300px]"}>
        <div className={"w-2/12 h-2"}></div>
        <div className="flex w-1/2 space-x-8 flex-wrap">
          {Object.entries(mainMenuList).map((value,i) =>
              Object.keys(value[1]).length > 2 &&
              <div key={i}  className={isToggle ? "flex w-full transition-all visible translate-x-[-125px] opacity-100" : "flex w-full transition-all invisible opacity-0"}>{Object.entries(value[1]).map((v,i) =>
                <MenuBtn menuHandler={menuHandler} key={v[0]+i} buttonName={v[0].toString()} buttonPath={v[1].toString()} isChild />)}
              </div>
            )}
          </div>
          <div className={"flex w-1/6 space-x-5 h-2"}></div>
      </div>
  </div>
  );
}

export default NavBar;