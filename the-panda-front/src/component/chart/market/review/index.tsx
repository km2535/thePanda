import React from 'react';

import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';
import { Doughnut } from 'react-chartjs-2';

ChartJS.register(ArcElement, Tooltip, Legend);

const MarketAnalysisReviewChart = ({review0, review1to49,review50to100,review100plus}:{review0:number, review1to49:number,review50to100:number,review100plus:number}) => {
   const data = {
   labels: ['0', '1~49','50~100','100+'],
  datasets: [
    {
      label: '리뷰건수',
      data: [review0,review1to49,review50to100,review100plus],
      backgroundColor: [
        'rgba(75, 192, 192, 0.7)', // 초록색
        'rgba(255, 206, 86, 0.7)', // 노란색
        'rgba(54, 162, 235, 0.7)', // 파란색
        'rgba(255, 99, 132, 0.7)', // 빨간색
        ],
        borderColor: [
          'rgba(75, 192, 192, 1)', // 초록색
          'rgba(255, 206, 86, 1)', // 노란색
          'rgba(54, 162, 235, 1)', // 파란색
          'rgba(255, 99, 132, 1)', // 빨간색
        ],
      borderWidth: 1,
    },
  ],
  };
  
  const options = {
    responsive: true,
    maintainAspectRatio: false,
    aspectRatio: 1,
    plugins: {
      legend: {
        display: false, // 레전드를 숨깁니다.
      },
    },
    
  };
     return(
      <> <Doughnut data={data} options={options} /></>
    )
  } 

 export default MarketAnalysisReviewChart;