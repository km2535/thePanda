import React, { useState } from 'react';
import ReactDatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';


const DateHandler = () => {
  const [selectedDate, setSelectedDate] = useState<Date | null>(null);

  const handleDateChange = (date: Date | null) => {
    setSelectedDate(date);
    // 여기에 선택한 날짜에 실행하고자 하는 함수를 호출하는 로직을 작성합니다.
    // 예를 들어, 선택한 날짜를 콘솔에 출력하는 경우:
    if (date) {
      console.log('선택한 날짜:', date);
    }
  };

  return (
    <div>
      <h1>날짜와 시간 선택</h1>
      <ReactDatePicker
        selected={selectedDate}
        onChange={handleDateChange}
        showTimeSelect
        dateFormat="yyyy년MM월dd일 HH시mm분"
      />
    </div>
  );
};

export default DateHandler;
