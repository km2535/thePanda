import { useAuth } from 'component/context/AuthContext';
import React, { useEffect, useState } from 'react';

const CoupangProductCard = ({ productDetail, btnName, eventHandler, keyword }: any) => {
  const { userInfo } = useAuth();
  const [sort, setSort] = useState("");
    const deleteHandler = (e: any) => {
        const id = userInfo?.userId + productDetail.productId + keyword;
        eventHandler(e, id);
  }
  useEffect(() =>{
    switch (productDetail.sortType) {
      case "scoreDesc": setSort("쿠팡 랭킹순");
        break;
      case "salePriceAsc": setSort("낮은 가격순");
        break;
      case "rankisalePriceDescng": setSort("높은 가격순");
        break;
      case "saleCountDesc": setSort("판매량순");
        break;
      case "latestAsc": setSort("최신순");
        break;
    }
  },[productDetail.sortType])
     return(
       <>
        <div className="bg-white shadow-md rounded-lg p-4 max-w-[600px] mb-5 mr-5">
          <div className="flex">
            <div className="mr-4 max-w-[150px] w-4/12">
                <img src={productDetail.image.endsWith('.gif') ? process.env.PUBLIC_URL + '/logo192.png' : productDetail.image } alt={productDetail.productName} className="w-full h-full object-cover rounded-md" />
            </div>
            <div className="flex flex-col justify-between w-6/12">
              <div>
                <div className="text-lg font-bold">
                    <a target='_blank' href={productDetail.link} className="text-blue-500 hover:underline" rel="noreferrer">{productDetail.productName}</a>
                </div>
                  <div className="text-gray-600">
                    <span className='line-through text-slate-500 text-sm mr-2'>{productDetail.originalPrice}원</span>
                    <span className='text-rose-600 font-extrabold'>{productDetail.salePrice}원</span>
                  </div>
                <div className="text-gray-500">
                  
                </div>
              </div>
              <div className="text-gray-500">
                  <div>배송 : {productDetail.deliveryInfo}</div>
                  <div>혜택 : {productDetail.rewqardInfo}</div>
                  <div className={'flex items-center'}>
                    <div className={'w-2/6'}>별점 : {productDetail.rating} </div>
                    <div className={'w-2/6'}>
                      {productDetail.rocket} 상품
                    </div>
                    {productDetail.rocketImgUrl !== "" ?
                    <div className='2/6'>
                      <img className={'w-20'} src={productDetail.rocketImgUrl} alt="" />
                    </div>
                    : ""}</div>
              </div>
              </div>
              <div className={'flex flex-col w-2/12'}>
              <div className="text-gray-500 font-bold text-xl text-right">{Number(productDetail.ranking) === 0 ? "광고" : productDetail.ranking + "위"}</div>
              <div className="text-gray-500 font-bold text-sm text-right">{keyword}</div>
              <div className="text-gray-500 font-bold text-sm text-right">{sort}</div>
                {userInfo !== null ? 
                  <div className="text-gray-500 font-bold text-sm text-right h-full flex items-end hover:text-black">
                      <button className='w-full cursor-pointer text-right' type='submit' onClick={deleteHandler}>{btnName}</button>
                </div>
                    : ""}
              </div>
          </div>
        </div>
       </>
    )
  } 

 export default CoupangProductCard;