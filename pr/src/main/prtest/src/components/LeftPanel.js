import React, { useState } from 'react';

function LeftPanel() {
    const [stMesege, setStMesege] = useState('상태 메시지를 입력하세요');
    const [isEditing, setIsEditing] = useState(false);
    const [inputValue, setInputValue] = useState(stMesege);

    // 상태 메시지 변경을 완료하는 함수
    const handleConfirmClick = () => {
        setStMesege(inputValue);
        setIsEditing(false);
    };

    // 상태 메시지 변경 버튼 클릭 시 편집 모드로 전환하는 함수
    const handleEditClick = () => {
        setIsEditing(true);
    };

    return (
        <div>
            <div className="profile">
                <p>오늘 방문자: 10명</p>
                <p>총 방문자: 1,234명</p>
                <img src="profile.jpg" alt="Profile" className="profile-img" />
                <div>
                    <p className="statusMesege">{stMesege}</p>
                    <button onClick={handleEditClick}>변경</button>
                </div>
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
            </div>
        </div>
    );
}

export default LeftPanel;
