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
const MarketAnalysisPriceChart = ({ maxPrice, minPrice, avePrice }: { maxPrice: number, minPrice: number, avePrice: number }) => {
  const labels = ['최소 가격', '평균 가격','최대 가격'];

  const data = {
    labels,
    datasets: [
      {
        label: '가격(원)',
        data: [minPrice,avePrice, maxPrice],
        backgroundColor: 'rgba(0, 148, 247, 0.7)',
      },
       {
         type: 'line' as const,
         label: '가격(원)',
         borderColor: 'rgb(255, 99, 132)',
         borderWidth: 2,
         fill: false,
         data: [minPrice,avePrice, maxPrice],
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

export default MarketAnalysisPriceChart;