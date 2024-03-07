import Button from 'component/buttons/navbarButton';
import React, { useEffect, useState } from 'react';

interface MenuProps { 
  mainMenuList: { [key: string]: string };
}

const NavBar: React.FC<MenuProps> = ({ mainMenuList }) => {
  const [menuKey, setMenuKey] = useState<string[]>([]);
  
  useEffect(() => {
    const keys: string[] = Object.keys(mainMenuList);
    setMenuKey(keys);
  }, [mainMenuList]);

  return (
     <div className="bg-white-800 text-brandFontColor py-4 border-b-zinc-500 shadow-sm">
      <div className="container mx-auto flex justify-between items-center">
        <div className='w-1/6 h-full'>
          <div className="bg-center bg-logo w-full h-10 bg-no-repeat cursor-pointer"></div>
        </div>
        <div className="flex space-x-8">
          {menuKey.map((value, index) => (
            <Button buttonPath={mainMenuList[value]} buttonName={value} key={index}/>
          ))}
        </div>
        <div className='flex space-x-5'>
          <Button buttonName='사용가이드' buttonPath='/guide'/>
          <Button buttonName='멤버십' buttonPath='/membership'/>
          <Button buttonName='로그인' buttonPath='/auth/sign-up'/>
        </div>
      </div>
    </div>
  );
}

export default NavBar;