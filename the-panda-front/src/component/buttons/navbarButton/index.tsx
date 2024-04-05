import React from 'react';
import { useNavigate } from 'react-router-dom';

interface linkBtn{
  buttonName: string,
  buttonPath: string,
  menuHandler:any,
  isChild : boolean
}
//path와 name을 받아 navi 버튼 역할
export default function MenuBtn(props : linkBtn) {
  const navigator = useNavigate();
  const buttonHandler = (e: any) => {
    const keyword = e.target.innerHTML;
    if (keyword === "발굴" || keyword === "분석" || keyword === "연관키워드")
      {props.menuHandler()}
    navigator(props.buttonPath);
  }
  return (
    <div className={"cursor-pointer hover:text-brandHoverFontColor mr-5"}>
      <span onClick={buttonHandler} >{props.buttonName}</span>
    </div>
  );
}

