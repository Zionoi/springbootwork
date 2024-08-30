import React, { useState } from 'react';

function MainMsg(){
    const [msg, setMsg] = useState('asdfasdf');
    const [isEditing, setIsEditing] = useState(false);
    const [inputValue, setInputValue] = useState(msg);

     // 상태 메시지 변경을 완료하는 함수
    const handleConfirmClick = () => {
        setMsg(inputValue);
        setIsEditing(false);
    };
     // 상태 메시지 변경 버튼 클릭 시 편집 모드로 전환하는 함수
     const handleEditClick = () => {
        setIsEditing(true);
    };
    return(
        <div className="main-msg">
            <p>{msg}&emsp;&emsp;&emsp;
            <button align={"right"} onClick={handleEditClick}>변경</button>
            </p>
            {isEditing && (
                    <div>
                        <input
                            type="text"
                            value={inputValue}
                            onChange={(e) => setInputValue(e.target.value)}
                        />
                        <button onClick={handleConfirmClick}>확인</button>
                    </div>
                )}


<a href="http://developers.naver.com" target="_blank">
    <img src="로고 이미지 위치" alt="NAVER 오픈 API" />
</a>


        </div>
    )
}
export default MainMsg;