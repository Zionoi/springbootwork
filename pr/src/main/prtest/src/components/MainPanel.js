import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import LeftPanel from './LeftPanel';
import Home from './Home';
import Diary from './Diary';
import Photo from './Photo';
import Board from './Board';
import Header from './Header';
import Guestbook from './Guestbook';
import MainMsg from './mainStsMsg';

function MainPanel() {
  return (
      <Router>
      <div style={{ display: 'flex' }}>
        <div className="left-panel">
            <Header/>
          <LeftPanel />
        </div>
        <div className="center-panel">
        <MainMsg/>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/diary" element={<Diary />} />
                <Route path="/photo" element={<Photo />} />
                <Route path="/board" element={<Board />} />
                <Route path="/guestbook" element={<Guestbook />} />
            </Routes>
        </div>
        <div className="right-panel">
          <nav>
            <Link to="/"><button type="button" class="btn btn-outline-primary">Home</button></Link><br/>
            <Link to="/diary"><button type="button" class="btn btn-outline-primary">Diary</button></Link><br/>
            <Link to="/photo"><button type="button" class="btn btn-outline-primary">사진첩</button></Link><br/>
            <Link to="/board"><button type="button" class="btn btn-outline-primary">게시판</button></Link><br/>
            <Link to="/guestbook"><button type="button" class="btn btn-outline-primary">방명록</button></Link><br/>
          </nav>
            <div class="btn-group-vertical" role="group" aria-label="Vertical button group">
                
            </div>
        </div>
      </div>
    </Router>
  );
}

export default MainPanel;
