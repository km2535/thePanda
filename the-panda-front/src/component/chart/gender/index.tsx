import React, { useEffect, useState } from 'react';
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';
import { Doughnut } from 'react-chartjs-2';
import GenderCountAPI from 'apis/response/datalab/genderCount';


ChartJS.register(ArcElement, Tooltip, Legend);

const ShoppingInsightGenderCountChart = ({ productDetail }: any) => {
  const [datas, setDatas] = useState([{
    period: "2023-03-01",
    ratio: 100,
    group: "f"
}]);
  const [femaleCount, setFemaleCount] = useState(0);
  const [maleCount, setMaleCount] = useState(0);

  useEffect(() => {
    if (productDetail !== "" && productDetail !== null && productDetail !== undefined) { 
      GenderCountAPI(productDetail?.keyword, productDetail?.category_id, setDatas);
    }
  }, [productDetail])
  useEffect(() => {
    if (datas !== null && datas.length > 0 && datas !== undefined) {
      let female = 0;
      let male = 0;
      datas.forEach(item => {
        if (item.group === 'f') {
          female += item.ratio;
        }
      }
      );
      datas.forEach(item => {
        if (item.group === 'm') {
          male += item.ratio;
        }
      }
      );
      setFemaleCount(female);
      setMaleCount(male);
    }
  }, [datas]);



  const data = {
    labels: ['여자', '남자'],
  datasets: [
    {
      label: '키워드 검색 성비율 (%)',
      data: [Math.round(femaleCount / (femaleCount + maleCount)* 100),  Math.round(maleCount / (maleCount+ femaleCount) * 100)],
      backgroundColor: [
        'rgba(245, 62, 62, 0.7)',
        'rgba(53, 98, 231, 0.7)',
      ],
      borderColor: [
        'rgba(255, 182, 193, 0.7)',
        'rgba(173, 216, 230, 0.7)',
      ],
      borderWidth: 1,
    },
  ],
  };
  
  const options = {
    responsive: true,
    maintainAspectRatio: false,
    aspectRatio: 1,
  };
     return(
       <div style={{ width: '70%', height: '70%' }}>
          <Doughnut data={data} options={options} />
      </div>
    )
  } 

 export default ShoppingInsightGenderCountChart;