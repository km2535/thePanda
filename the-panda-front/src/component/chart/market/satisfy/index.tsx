import React from 'react';

import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';
import { Doughnut } from 'react-chartjs-2';

ChartJS.register(ArcElement, Tooltip, Legend);

 const MarketAnalysisSatisfyChart = ({satisfy50minus, satisfy50to60,satisfy60to70,satisfy70to80,satisfy80to90,satisfy90to100}:{satisfy50minus:number, satisfy50to60:number,satisfy60to70:number,satisfy70to80:number,satisfy80to90:number,satisfy90to100:number}) => {
     const data = {
   labels: ['50%미만', '50~60(%)','60~70(%)','70~80(%)','80~90(%)','90~100(%)'],
  datasets: [
    {
      label: '만족도',
      data: [satisfy50minus,satisfy50to60,satisfy60to70,satisfy70to80,satisfy80to90,satisfy90to100],
      backgroundColor: [
  '#4D4DFF',
  '#FF4D4D',
  '#4DFF4D',
  '#FFFF4D',
  '#9B59B6',
  '#E67E22'
],
borderColor: [
  '#4D4DFF',
  '#FF4D4D',
  '#4DFF4D',
  '#FFFF4D',
  '#9B59B6',
  '#E67E22'
]
,
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

 export default MarketAnalysisSatisfyChart;