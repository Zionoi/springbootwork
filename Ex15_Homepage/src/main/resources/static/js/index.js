// mainImage 요소를 가져옴
	const mainImage = document.getElementById('mainImage');
	
	// mainImage를 클릭했을 때 이벤트 리스너 추가
	mainImage.addEventListener('click', function() {
		// 이미지 페이드 아웃
		mainImage.classList.add('fade-out');

		// 페이드 아웃이 완료된 후
		setTimeout(function() {
			// 여기에 이미지 소스를 변경하는 코드를 추가할 수 있음
			// 예: mainImage.src = 'new_image.jpg';

			// 이미지 페이드 인
			mainImage.classList.remove('fade-out');
		}, 1000); // setTimeout의 시간은 CSS 전환 시간과 일치해야 함
	});