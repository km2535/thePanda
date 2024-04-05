import React, { useEffect, useState } from 'react';
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';
import { Doughnut } from 'react-chartjs-2';

import AgeCountAPI from 'apis/response/datalab/ageCount';
ChartJS.register(ArcElement, Tooltip, Legend);

const ShoppingInsightAgeCountChart = ({ productDetail }: any) => {
  const [ageData, setAgeData] = useState<any[]>([]);

  useEffect(() => {
    if (productDetail !== "" && productDetail !== null && productDetail !== undefined) {  
      AgeCountAPI(productDetail?.keyword, productDetail?.category_id, setAgeData);
    }
  }, [productDetail]);

  useEffect(() => {
    // Calculate total ratio for each age group
    const ageGroups = Array.from({ length: 6 }, (_, i) => `${(i + 1) * 10}`);
    const totalRatiosByGroup: { [key: string]: number } = {};
    ageGroups.forEach(group => {
      totalRatiosByGroup[group] = ageData.reduce((total, item) => {
        if (item.group === group) {
          return total + item.ratio;
        }
        return total;
      }, 0);
    });

    // Calculate ratios as percentages
    const totalRatioSum = Object.values(totalRatiosByGroup).reduce((sum, ratio) => sum + ratio, 0);
    const percentageData = ageGroups.map(group => {
      const ratio = totalRatiosByGroup[group];
      return ((ratio / totalRatioSum) * 100).toFixed(1);
    });

    // Chart data
    const labels = ageGroups.map(group => `${group}대`);
    const data = {
      labels,
      datasets: [
        {
          label: '세대별 클릭 비율(%)',
          data: percentageData,
          backgroundColor: [
               'rgba(255, 69, 0, 0.7)', // Dark Orange
                'rgba(0, 206, 209, 0.7)', // Turquoise
                'rgba(255, 165, 0, 0.7)', // Orange
                'rgba(147, 112, 219, 0.7)', // Medium Purple
                'rgba(30, 144, 255, 0.7)', // Dodger Blue
                'rgba(255, 99, 132, 0.7)', // Coral Red
          ],
        },
      ],
    };

    // Set chart data
    setChartData(data);
  }, [ageData]);

  // State for chart data
  const [chartData, setChartData] = useState<any>(null);

  // Chart options
  const options = {
    responsive: true,
    plugins: {
      legend: {
        position: 'top' as const,
      },
     
    },
  };

  return (
    <div style={{ width: '70%', height: '70%' }}>
      {chartData &&   <Doughnut data={chartData} options={options} />}
    </div>
  );
};

export default ShoppingInsightAgeCountChart;
