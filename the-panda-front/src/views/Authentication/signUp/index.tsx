import { SNS_SIGN_IN_URL } from 'apis';
import React from 'react';
import { useNavigate } from 'react-router-dom';

export default function SignUp() {
  const navigate = useNavigate();
  const onSnsSignInButtonClickHandler = (type: 'kakao' | 'naver' | 'google') => {
    window.location.href = SNS_SIGN_IN_URL(type);
  }
  const logoClickHandler = () => {
    navigate("/")
  }
  return (
    <div className='w-screen h-screen flex justify-center items-center'>
      <div className={'flex flex-col w-[450px] h-[350px] justify-evenly shadow-2xl rounded-xl'}>
        <div className={'bg-center bg-cover bg-logo w-full h-[150px] bg-no-repeat cursor-pointer'} onClick={logoClickHandler}></div>
        <div className={'text-center h-[50px] leading-10'}>{'Sns login'}</div>
        <div className={'flex '}>
          <div className={'w-1/3 flex justify-center'}>
            <div className={'bg-center  bg-kakaoSign bg-100% h-16 bg-no-repeat w-16 cursor-pointer hover:scale-110	transition ease-in'} onClick={()=>onSnsSignInButtonClickHandler('kakao')}></div>
          </div>
          <div className={'w-1/3 flex justify-center'}>
            <div className={'bg-center bg-naverSign bg-100% h-16 bg-no-repeat w-16 cursor-pointer hover:scale-110 transition ease-in'} onClick={()=>onSnsSignInButtonClickHandler('naver')}></div>
          </div>
          <div className={'w-1/3 flex justify-center'}>
            <div className={'bg-center bg-googleSign bg-100% h-16 bg-no-repeat w-16 cursor-pointer hover:scale-110 transition ease-in'} onClick={()=>onSnsSignInButtonClickHandler('google')}></div>
          </div>
        </div>
      </div>
    </div>
  );
}

