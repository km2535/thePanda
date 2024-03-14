import { DataCollector } from 'apis/request/collection';
import React, { useState } from 'react';

// 카테고리별 top 500 데이터를 수집하는 페이지입니다. 
export default function RealTimeDataManager() {
  const [message, setMessage] = useState<string>("");
  const dataCollectionStarter = () => {
    //수집 api를 요청합니다.
    DataCollector(setMessage)
  }
  return (
    <div>
      {message !== "" ? message : ""}
      <button onClick={dataCollectionStarter}>수집</button>
    </div>
  );
}

