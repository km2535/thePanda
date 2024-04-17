import React from 'react';
import parse from 'html-react-parser';
import { useAuth } from 'component/context/AuthContext';
import { v4 as uuidv4 } from 'uuid';

const NavaerProductCart = ({productDetail, btnName,eventHandler} :any) => {
  const { userInfo } = useAuth();
  const deleteHandler = (e: any) => {
    const id = userInfo?.userId + productDetail.productId + productDetail.keyword;
    eventHandler(e, id);
  }
  return (
       <>
     <div key={uuidv4()}  className="bg-white shadow-md rounded-lg p-4 max-w-[600px]  mb-5 mr-5">
      <div className="flex">
        <div className="mr-4 max-w-[150px] w-4/12">
          <img src={productDetail.image} alt={productDetail.keyword} className="w-full h-full object-cover rounded-md" />
        </div>
        <div className="flex flex-col justify-between w-6/12">
          <div>
            <div className="text-lg font-bold">
              <a target='_blank' href={productDetail.link} className="text-blue-500 hover:underline" rel="noreferrer">{parse(productDetail.title)}</a>
            </div>
            <div className="text-gray-600">{productDetail.lprice} 원</div>
            <div className="text-gray-500">
              {productDetail.category1}
              {productDetail.category2 ? " > " + productDetail.category2 : ""}
              {productDetail.category3 ? " > " + productDetail.category3 : ""}
              {productDetail.category4 ? ` > ${productDetail.category4}` : ""}
            </div>
          </div>
          <div className="text-gray-500">
            <div>브랜드: {productDetail.brand}</div>
            <div>판매몰: {productDetail.mallName}</div>
            <div>생산자: {productDetail.maker}</div>
          </div>
        </div>
          <div className={'flex flex-col w-2/12'}>
          <div className="text-gray-500 font-bold text-xl text-right">{productDetail.rank}위</div>
            <div className="text-gray-500 font-bold text-sm text-right">{productDetail.keyword}</div>
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

 export default NavaerProductCart;