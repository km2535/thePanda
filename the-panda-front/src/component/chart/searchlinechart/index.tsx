
import { Line } from 'react-chartjs-2';

import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';
import { useEffect, useState } from 'react';
import SearchCountAPI from 'apis/response/datalab/searchCount';
ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
); 
const ShoppingInsightSearchCountChart = ({productDetail}:any) => {
  const [datas, setDatas] = useState([{ period: "2024-03-30", ratio: 3.0 }]);
  const [optionForSearch, setOptionForSearch] = useState<any>({ year: 0, range: "date" });
  
  useEffect(() => {
    //api 요청
    if (productDetail!== "" && productDetail !== null && productDetail !== undefined) {  
     SearchCountAPI(productDetail?.keyword, productDetail?.category_id, optionForSearch?.year,optionForSearch?.range,setDatas);
    }
  },[optionForSearch, productDetail])

  const optionHandler = (e: any) => {
    setOptionForSearch({"year" : parseInt(e.target.id), "range": e.target.getAttribute('data-target')})
  }
  const options = {
    responsive: true,
    plugins: {
      legend: {
        position: 'top' as const,
      },
      title: {
        display: false,
      },
    },
  };

  const labels =  datas.length > 0 ? datas.map((obj) => obj["period"]) : [];

  const data = {
    labels,
    datasets: [
      {
        label: '키워드 클릭 건수',
        data:  datas.length > 0  ? (optionForSearch?.range !=="date" ?  datas?.map((item) => 
          Math.round((item["ratio"] / datas[datas?.length - 1]["ratio"]) * productDetail?.total_qc_cnt)  
        ) : datas.map((item) => 
          Math.round(((item["ratio"] / datas[datas?.length - 1]["ratio"]) * productDetail?.total_qc_cnt)/30)  
        ) ) : "",
        borderColor: 'rgb(253, 0, 0)',
        backgroundColor: 'rgba(240, 120, 120, 0.5)',
        yAxisID: 'y-axis-0'
      }
    ],
  };
 
  return (
    <div>
      <div>
        <div className={'cursor-pointer'} data-target="date" id="0" onClick = {optionHandler}>1개월</div>
        <div className={'cursor-pointer'} data-target="month" id="1" onClick = {optionHandler}>1년</div>
        <div className={'cursor-pointer'} data-target="month" id="3" onClick = {optionHandler}>3년</div>
      </div>
     <Line options={options} data={data} />;
    </div>
  );
};

 export default ShoppingInsightSearchCountChart;