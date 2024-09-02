import React, { useState } from 'react';
import axios from 'axios';

function SignUp() {
    const [id, setId] = useState('');
    const [pass, setPass] = useState('');
    const [nickname, setNickname] = useState('');

    const handleSignUp = () => {
        axios.post('http://localhost:8080/api/auth/signup', { id, pass, nickname })
            .then(response => {
                if (response.data === "User registered successfully") {
                    alert("Signup successful! Please login.");
                } else {
                    alert(response.data);
                }
            })
            .catch(error => console.error('Error:', error));
    };

    return (
        <div>
            <h2>Sign Up</h2>
            <input type="text" value={id} onChange={(e) => setId(e.target.value)} placeholder="ID" />
            <input type="password" value={pass} onChange={(e) => setPass(e.target.value)} placeholder="Password" />
            <input type="text" value={nickname} onChange={(e) => setNickname(e.target.value)} placeholder="Nickname" />
            <button onClick={handleSignUp}>Sign Up</button>
        </div>
    );
}

export default SignUp;
