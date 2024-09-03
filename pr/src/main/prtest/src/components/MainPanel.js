import React from 'react';
import { Route, Routes, Link, useNavigate } from 'react-router-dom';
import LeftPanel from './LeftPanel';
import Home from './Home';
import Diary from './Diary';
import Photo from './Photo';
import Board from './Board';
import Header from './Header';
import Guestbook from './Guestbook';
import MainMsg from './mainStsMsg';
import InputDiary from './InputDiary';
import GetDiary from './GetDiary';

function MainPanel({ onLogout }) { // onLogout props 추가
  const navigate = useNavigate();

  const handleLogout = () => {
    onLogout(); // 부모 컴포넌트의 로그아웃 핸들러 호출
    navigate('/login'); // 로그인 페이지로 리다이렉트
  };

  return (
    <div style={{ display: 'flex' }}>
      <div className="left-panel">
        <Header />
        <LeftPanel />
      </div>
      <div className="center-panel">
        <MainMsg />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/diary" element={<Diary />} />
          <Route path="/photo" element={<Photo />} />
          <Route path="/board" element={<Board />} />
          <Route path="/guestbook" element={<Guestbook />} />
          <Route path="/inputDiary/:date" element={<InputDiary />} /> {/* InputDiary 페이지 라우팅 */}
          <Route path="/getDiary/:dNum" element={<GetDiary />} />
        </Routes>
      </div>
      <div className="right-panel">
        <nav>
          <Link to="/"><button type="button" className="btn btn-outline-primary">Home</button></Link><br/>
          <Link to="/diary"><button type="button" className="btn btn-outline-primary">Diary</button></Link><br/>
          <Link to="/photo"><button type="button" className="btn btn-outline-primary">사진첩</button></Link><br/>
          <Link to="/board"><button type="button" className="btn btn-outline-primary">게시판</button></Link><br/>
          <Link to="/guestbook"><button type="button" className="btn btn-outline-primary">방명록</button></Link><br/>
        </nav>
        <button onClick={handleLogout} className="btn btn-outline-danger">로그아웃</button>
      </div>
    </div>
  );
}

export default MainPanel;
