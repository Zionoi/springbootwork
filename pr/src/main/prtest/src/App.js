import React, { useState } from 'react';
import './App.css';
import MainPanel from './components/MainPanel';
import 'bootstrap/dist/css/bootstrap.min.css';
import Login from './components/Login';

function App() {
  // 로그인 상태를 관리할 state 추가
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  // 로그인 성공 시 호출될 함수
  const handleLoginSuccess = () => {
    setIsLoggedIn(true);
  };

  return (
    <div className="App">
      {isLoggedIn ? (
        <div className="main-panel">
          <MainPanel />
        </div>
      ) : (
        <Login onLoginSuccess={handleLoginSuccess} />
      )}
    </div>
  );
}

export default App;
