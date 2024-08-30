import React, { useState, useEffect } from 'react';

function Visit() {
  // 방문자 수 상태 정의
  const [visitorCount, setVisitorCount] = useState(0);

  // 날짜 확인 함수
  const getToday = () => {
    const today = new Date();
    return `${today.getFullYear()}-${today.getMonth() + 1}-${today.getDate()}`;
  };

  // 방문자 수 증가 함수
  const incrementVisitorCount = () => {
    const today = getToday();
    const storedDate = localStorage.getItem('visitDate');
    
    // 방문 날짜가 오늘이 아니면 초기화
    if (storedDate !== today) {
      localStorage.setItem('visitDate', today);
      localStorage.setItem('visitorCount', 1);
      setVisitorCount(1);
    } else {
      // 방문 날짜가 오늘이면 증가
      const count = parseInt(localStorage.getItem('visitorCount') || 0) + 1;
      localStorage.setItem('visitorCount', count);
      setVisitorCount(count);
    }
  };

  // 페이지가 로드될 때 실행
  useEffect(() => {
    incrementVisitorCount();
  }, []);

  return (
    <div className="Visit">
      <h1>일일 방문자 수</h1>
      <p>오늘의 방문자 수: {visitorCount}</p>
    </div>
  );
}

export default Visit;