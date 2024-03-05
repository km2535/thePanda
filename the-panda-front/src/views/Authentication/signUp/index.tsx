import { SNS_SIGN_IN_URL } from 'apis';
import React from 'react';
import './style.css';

export default function SignUp() {
   
  const onSnsSignInButtonClickHandler = (type: 'kakao' | 'naver' | 'google') => {
    window.location.href = SNS_SIGN_IN_URL(type);
  }
  return (
    <div id='sign-up-wrapper'>
      <div className='sign-up-image'></div>
      <div className={'sign-up-container'}>
        <div className={'sign-up-box'}>
          <div className={'sign-up-title'}>{'키워드 분석 서비스'}</div>
          <div className={'sign-up-content-box'}>
            <div className={'sign-up-content-sns-sign-in-box'}>
              <div className={'sign-up-content-sns-sign-in-title'}>{'Sns login'}</div>
              <div className={'sign-up-content-sns-sign-in-button-box'}>
                <div className={'kakao-sign-in-button'} onClick={()=>onSnsSignInButtonClickHandler('kakao')}></div>
                <div className={'naver-sign-in-button'}onClick={()=>onSnsSignInButtonClickHandler('naver')}></div>
                <div className={'google-sign-in-button'}onClick={()=>onSnsSignInButtonClickHandler('google')}></div>
              </div>
            </div>
            <div className={'sign-up-content-divider'}></div>
            <div className={'sign-up-content-input-box'}>
            </div>
            
          </div>
        </div>
      </div>
    </div>
  );
}

