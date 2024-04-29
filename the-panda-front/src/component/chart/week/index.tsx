import WeekSearchCountAPI from 'apis/response/datalab/weekendsCount';
import React, { useEffect, useState } from 'react';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';
import { Bar } from 'react-chartjs-2';
import getOneMonthAgo from 'util/dateCal';

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);


const ShoppingInsightWeekendsCountChart = ({ productDetail }: any) => {
   const [datas, setDatas] = useState<Record<string, number> | undefined>(undefined);
  
   useEffect(() => {
    if (productDetail !== "" && productDetail !== null && productDetail !== undefined) { 
      WeekSearchCountAPI(productDetail?.keyword, productDetail?.category_id, 0,"date", productDetail?.total_qc_cnt).then((data) => setDatas(data))
    } 
   }, [productDetail])
    const labels =  datas ? Object.keys(datas) : [];
    
    const data = {
        labels,
        datasets: [
      {
        label: '요일별 클릭량',
        data: labels.map((label) => datas ? datas[label] : 0),
        backgroundColor: 'rgba(0, 148, 247, 0.7)',
      }
    ],
      };
    const options = {
          responsive: true,
          plugins: {
            legend: {
              position: 'top' as const,
            },
            title: {
              display: true,
              text: `${productDetail?.update_date} ~ ${getOneMonthAgo(productDetail?.update_date)}`,
            }
          },
      };
  
     return(
      <> <Bar options={options} data={data} /></> 
    ) 
  } 

 export default ShoppingInsightWeekendsCountChart;