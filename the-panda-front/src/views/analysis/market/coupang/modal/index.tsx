import React, { useEffect,  useState } from 'react';
import { CSVLink } from 'react-csv';
import { CoupangProducts } from 'types/productTypes/productType,';

const ModalInCoupang = ({ setModal, datas, keyword }: { setModal: React.Dispatch<React.SetStateAction<string>>, datas: CoupangProducts,keyword:string }) => {
   const closeHandler = () => {
     setModal("")
  }  
  const [height, setHeight] = useState(1000);
  const [excelData, setExcelData] = useState<any>();

  useEffect(() => {
    setHeight(window.document.body.clientHeight*1.2);
  }, [])
  useEffect(() => {
    const header = ["순위", "상품명", 
    "리뷰수","별점", "원가","세일가","배송","혜택" ,"정보", "상품 url","광고여부"
  ];
    const result: any[][] = [];
    datas[keyword].map((data) => {
      const exceldatas: string[] = [];
      exceldatas.push(data.ranking);
      exceldatas.push(data.productName);
      exceldatas.push(data.reviewCount.replace(/[^\d]/g, ''));
      exceldatas.push(data.isAd==="true" ? data.ranking + "(광고)":data.ranking);
      exceldatas.push(data.originalPrice);
      exceldatas.push(data.salePrice);
      exceldatas.push(data.deliveryInfo);
      exceldatas.push(data.rewardInfo);
      exceldatas.push(data.rocket);
      exceldatas.push(data.productUrl);
      exceldatas.push(data.isAd);
      result.push(exceldatas);
      return exceldatas;
    })
    result.unshift(header)
    setExcelData(result);
  }, [datas, keyword])
   return (
       <>
         <div style={{height}} className={`absolute top-0 left-0 z-50 w-full bg-white backdrop-blur-3xl overflow-y-hidden`} >
         <div className={'flex w-full h-full justify-center mt-10 mb-20 flex-col'}>
           <div className={'w-5/6 max-h-[1050px] overflow-scroll m-auto mb-2 mt-2 shadow-2xl'}>
             <div className='bg-slate-100 w-full flex flex-col '>           
                <div onClick={closeHandler} className={'flex sticky justify-end top-0 z-50 font-semibold cursor-pointer p-2 text-[1.5rem] bg-white'}>
                X
                </div>           
               <table>
                 <thead className={'sticky top-[52px]'}>
                   <tr className={'bg-white'}>
                     <th>순위</th>
                     <th>상품명</th>
                     <th>리뷰수(별점)</th>
                     <th>가격</th>
                     <th>배송</th>
                     <th>정보</th>
                   </tr>
                 </thead>
                 <tbody>
                 {datas[keyword]?.map((data) => 
                   <tr key={data.vendorId + data.productName + data.ranking}>
                     <td className={'w-1/12 text-center'}>{data.isAd === "true" ? data.ranking+"(광고)" : data.ranking}</td>
                     <td className={'font-black hover:text-blue-700 w-6/12'}><a target='_blank' href={data.productUrl} rel="noreferrer">{data.productName}</a></td>
                     <td className={'w-1/12 text-center'}>{data.reviewCount}({data.rating})</td>
                     <td className={'w-1/12 text-center'}>{data.salePrice}</td>
                     <td className={'w-1/12 text-center'}>{data.deliveryInfo}</td>
                     <td className={'w-1/12 text-center'}>{data.rocketName}</td>
                    </tr>                    
                  )}     
                 </tbody>
               
               </table>
             </div>
           </div>
           <div className={'text-right w-5/6 m-auto'}>
             {(excelData !== undefined && excelData.length > 1) && 
             <CSVLink data={excelData} filename={`${keyword}-coupang-productList.csv`}>
                <button className={'cursor-pointer bg-buttonColor p-2 text-center hover:bg-brandHoverFontColor rounded-xl mt-2 mb-2 text-gray-100 '}>키워드 검색 목록 다운</button>
             </CSVLink>
            }
           </div>
           </div>
         </div>
       </>
    )
  } 

 export default ModalInCoupang;