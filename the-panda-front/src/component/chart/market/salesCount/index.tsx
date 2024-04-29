import React from 'react';

import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';
import { Chart } from 'react-chartjs-2';

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

 const MarketAnalysisSalesCountChart = ({ maxSales, minSales, aveSales }: { maxSales: number, minSales: number, aveSales: number }) => {
     const labels = ['최소 판매량', '평균 판매량','최대 판매량'];

  const data = {
    labels,
    datasets: [
      {
        label: '판매량(건)',
        data: [minSales,aveSales, maxSales],
        backgroundColor: 'rgba(0, 148, 247, 0.7)',
      },
       {
         type: 'line' as const,
         label: '판매량(건)',
         borderColor: 'rgb(255, 99, 132)',
         borderWidth: 2,
         fill: false,
         data: [minSales,aveSales, maxSales],
    },
    ],
  };

  const options = {
    responsive: true,
    plugins: {
      legend: {
        display:false,
      },
     
     
    },
  };

  return (
    <>
      <Chart  type='bar' options={options} data={data} />
    </>
  );
  } 

 export default MarketAnalysisSalesCountChart;