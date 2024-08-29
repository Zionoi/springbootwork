// Home.js
import React, { useEffect, useState } from 'react';
import axios from 'axios';

function Home() {
    const [mainRply, setMainRply] = useState([]);
   const [test, setTest] = useState();	 
   const [reply_writer, setName] = useState('');
   const [reply_content, setComent] = useState('');
	 useEffect(() => {  // 웹 열릴때 자동실행되는 useEffect 사용
		 axios.get('/api/mainRply')     // axios.get 통신
		 	  .then(result => { //통신 성공시
				   console.log('result. : ',result)
				   console.log('result.data : ',result.data)
                   setMainRply(result.data);
                   console.log('hello : ',{test})
			   })   // 실패시 catch는 생략함
	 },[]); // 로드되고 한번만 실행

     useEffect(()=>{
        axios.get('/api/test')
             .then(result=> {
                // console.log('result. : ',result)
				// console.log('result.data : ',result.data)
                setTest(result.data);
             })
     })


  return (
    <div>
      <h3>HOME</h3>
        <h1>{test}</h1>

      <img src="main-photo.jpg" alt="Home" width={"70%"}/>
      <br/><hr/>
      <div>
          <p>asdf</p>
          <tr>
              <td>
                <input className="inputName" onChange={e=>{setName(e.target.value)}}/>
                <input className="inputComent" onChange={e=>{setComent(e.target.value)}}/>
                <button onClick={()=>{
                  axios.post('/api/insertComent',{reply_writer: reply_writer, reply_content:reply_content}
                       .then((result)=>{
                        console.log('리액트 서버로 데이터 보내기 : ',result);
                       })
                  )
                }}>코멘트</button>
              </td>
            </tr>
      {
        mainRply.map((rList)=>{
          return (

          <div>
            
            <tr>
                <td>{rList.reply_no}</td>         
            
          
                <td>{rList.reply_writer}</td>         
            
          
                <td>{rList.reply_content}</td>         
            
          
                <td>{rList.create_date}</td>         
            </tr>
            </div>
          )
        })
      }
      </div>
    </div>
  );
}

export default Home;
