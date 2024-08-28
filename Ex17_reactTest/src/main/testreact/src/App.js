import {useEffect, useState} from 'react';
import './App.css';
import axios from 'axios';

function App() {
	 const [hello, setHello] = useState();
   const [menuList, setMenuList] = useState([]);	 
	 useEffect(() => {  // 웹 열릴때 자동실행되는 useEffect 사용
		 axios.get('/api/test')     // axios.get 통신
		 	  .then(result => { //통신 성공시
				   console.log(result)
				   console.log(result.data)
           setHello(result.data);
				   
			   })   // 실패시 catch는 생략함
	 },[]); // 로드되고 한번만 실행
  return (
    <div className="App">
      <h3>서머에서 들어온 값 : {hello}</h3>
      <br/>
      <hr></hr>
      <br/>
      {
        menuList.map((menu)=>{
          return (
          <div>{menu.name}</div>
          )
        })
      }
      {/* 데이터 가져오기 */}
      <button onClick={()=>{
        axios.get('/api/menuall')
             .then(result => {
              console.log(result);
              setMenuList(result.data);
             })
             .catch(()=>{
              console.log("실패");
             })
      }}>서버에서 메뉴가져오기</button>

      {/* 서버로 데이터 보내기  // 데이터 보내줄땐 .post('맵핑경로', {보내줄 데이터})*/}
      <button onClick={()=>{
        axios.post('/api/addmenu',{restaurant:'꾸이한끼', name:'불갈비덮밥', price:'12000', type:'KR', taste:'HOT'})  
             .then((result)=>{
                console.log('리액트 서버로 데이터 보내기 : ',result);
             })
      }}>메뉴 데이터 보내기</button>

    </div>
  );
}

export default App;
