import React from 'react';
import { useNavigate } from 'react-router-dom';

interface linkBtn{
  buttonName: string,
  buttonPath: string,
  
  isChild : boolean
}
//path와 name을 받아 navi 버튼 역할
export default function MenuBtn(props : linkBtn) {
  const navigator = useNavigate();
  const buttonHandler = () => {
    navigator(props.buttonPath);
  }
  return (
    <div className={"cursor-pointer hover:text-brandHoverFontColor mr-2"}>
      <div onClick={buttonHandler}>{props.buttonName}</div>
    </div>
  );
}

