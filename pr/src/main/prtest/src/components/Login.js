// src/components/Login.js

import React, { useState } from 'react';
import axios from 'axios';

const Login = () => {
    const [userid, setUserid] = useState('');
    const [pass, setPass] = useState('');
    const [message, setMessage] = useState('');

    const handleLogin = async () => {
        try {
            const response = await axios.post('http://localhost:8080/api/auth/login', {
                userid,
                pass,
            });
    
            console.log('서버 응답:', response); // 응답 데이터 확인
            console.log('응답 데이터:', response.data); // 응답 데이터에서 토큰 확인
    
            if (response.data) {
                localStorage.setItem('token', response.data);
                setMessage('로그인 성공!');
            } else {
                setMessage('잘못된 사용자 이름 또는 비밀번호');
            }
        } catch (error) {
            console.error('로그인 오류:', error);
            setMessage('로그인 실패');
        }
    };

    return (
        <div>
            <input
                type="text"
                placeholder="사용자 아이디"
                value={userid}
                onChange={(e) => setUserid(e.target.value)}
            />
            <input
                type="password"
                placeholder="비밀번호"
                value={pass}
                onChange={(e) => setPass(e.target.value)}
            />
            <button onClick={handleLogin}>로그인</button>
            <p>{message}</p>
        </div>
    );
};

export default Login;
