import React, { useEffect, useState } from 'react';
import { CSVLink } from 'react-csv';
import { GmarketProducts } from 'types/productTypes/productType,';

const ModalInGmarket = ({ setModal, datas, keyword }: { setModal: React.Dispatch<React.SetStateAction<string>>, datas: GmarketProducts, keyword: string }) => {
  const [height, setHeight] = useState(1000);
  const [excelData, setExcelData] = useState<any>();
const closeHandler = () => {
     setModal("")
  }  
  useEffect(() => {
    setHeight(window.document.body.clientHeight*1.2);
  }, [])
  useEffect(() => {
    const header = ["순위", "상품명", 
    "리뷰수","원가", "세일가","배송정보","판매량" ,"만족도", "상품 url"
  ];
    const result: any[][] = [];
    datas[keyword].map((data,i) => {
      const exceldatas: string[] = [];
      exceldatas.push((i+1)+"");
      exceldatas.push(data.productName);
      exceldatas.push(data.reviewCount.replace(/[^\d]/g, ''));
      exceldatas.push(data.originalPrice);
      exceldatas.push(data.salesPrice);
      exceldatas.push(data.freeDelivery  + " " + data.DeliveryInfo);
      exceldatas.push(data.sellingCount);
      exceldatas.push(data.awardPoints);
      exceldatas.push(data.productUrl);
      result.push(exceldatas);
      return exceldatas;
    })
    result.unshift(header)
    setExcelData(result);
  }, [datas, keyword])
     return(
      <>
         <div style={{height}} className={`absolute top-0 left-0 z-50 w-full bg-white backdrop-blur-3xl overflow-y-hidden`} >
         <div className={'flex w-full h-full justify-start mt-10 mb-20 flex-col'}>
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
                     <th>리뷰수</th>
                     <th>가격</th>
                     <th>배송</th>
                     <th>판매량</th>
                     <th>만족도</th>
                   </tr>
                 </thead>
                 <tbody>
                 {datas[keyword]?.map((data,i) => 
                   <tr key={data.productName + i}>
                     <td className={'w-1/12 text-center'}>{i+1}</td>
                     <td className={'font-black hover:text-blue-700 w-5/12'}><a target='_blank' href={data.productUrl} rel="noreferrer">{data.productName}</a></td>
                     <td className={'w-1/12 text-center'}>{data.reviewCount}</td>
                     <td className={'w-1/12 text-center'}>{data.salesPrice}</td>
                     <td className={'w-2/12 text-center'}>{data.DeliveryInfo + " " + data.freeDelivery}</td>
                     <td className={'w-1/12 text-center'}>{data.sellingCount}</td>
                     <td className={'w-1/12 text-center'}>{data.awardPoints !== ""  && parseInt(data.awardPoints.split(" ")[1])+"%"}</td>
                    </tr>                    
                  )}     
                 </tbody>
               
               </table>
             </div>
           </div>
           <div className={'text-right w-5/6 m-auto mt-2 mb-2'}>
             {(excelData !== undefined && excelData.length > 1) && 
             <CSVLink data={excelData} filename={`${keyword}-gmarket-productList.csv`}>
                <button className={'cursor-pointer bg-buttonColor p-2 text-center hover:bg-brandHoverFontColor rounded-xl mb-2 text-gray-100 '}>키워드 검색 목록 다운</button>
             </CSVLink>
            }
           </div>
           </div>
         </div>
       </>
    )
  } 

 export default ModalInGmarket;