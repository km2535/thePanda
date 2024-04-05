import React from 'react';

interface MenuProps { 
  mainMenuList: { [key: string]: { [key: string]: string } },
  menuHandler: any
}

const DepthNavBtn: React.FC<MenuProps> = ({ mainMenuList, menuHandler }) => {
  const handlerMenuHandler = () => {
    menuHandler();
  }
  return (
    <>
      
        {Object.keys(mainMenuList).map((key, i) =>
          <div key={i} onClick={handlerMenuHandler} >
            <div className={"cursor-pointer hover:text-brandHoverFontColor"} >{key}</div>
          </div>
        )}
      
      
    </>
  );
}
export default DepthNavBtn;
