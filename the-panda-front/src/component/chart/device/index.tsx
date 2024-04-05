import DeviceCountAPI from 'apis/response/datalab/deviceCount';
import React, { useEffect, useState } from 'react';
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';
import { Doughnut } from 'react-chartjs-2';


ChartJS.register(ArcElement, Tooltip, Legend);


 const ShoppingInsightDeviceCountChart = ({productDetail}:any) => {
  const [datas, setDatas] = useState([{
    period: "2023-03-01",
    ratio: 100,
    group: "mo"
}]);
  const [pcCount, setPcCount] = useState(0);
  const [mobileCount, setMobileCount] = useState(0);

  useEffect(() => {
    if (productDetail !== "" && productDetail !== null && productDetail !== undefined) { 
      DeviceCountAPI(productDetail?.keyword, productDetail?.category_id, setDatas);
    }
  }, [productDetail])
  useEffect(() => {
    if (datas !== null && datas.length > 0 && datas !== undefined) {
      let female = 0;
      let male = 0;
      datas.forEach(item => {
        if (item.group === 'pc') {
          female += item.ratio;
        }
      }
      );
      datas.forEach(item => {
        if (item.group === 'mo') {
          male += item.ratio;
        }
      }
      );
      setPcCount(female);
      setMobileCount(male);
    }
  }, [datas]);



  const data = {
    labels: ['PC', '모바일'],
  datasets: [
    {
      label: '키워드 검색 성비율 (%)',
      data: [Math.round(pcCount / (pcCount + mobileCount)* 100),  Math.round(mobileCount / (mobileCount+ pcCount) * 100)],
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

 export default ShoppingInsightDeviceCountChart;