package com.study.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

// @Data 어노테이션을 달면 게터세터투스트링 등 다 자동생성됨. outline통해서 확인가능

// @Getter or @Setter 하면 각 메소드만 생성됨 둘다 넣으면 둘다 생성됨.

// @NoArgsConstructor : 빈생성자 생성됨

// @AllArgsConstructor : 모든 인자 받는 생성자 생성됨.

// @ToString : toString 생성

// @NonNull : null값 들어오면 오류

/*@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString*/

// Data만 쓰면 생성자가 하나밖에 없음 보통 이렇게 어노테이션 조합해서 사용함 
@Data
@NoArgsConstructor	
@AllArgsConstructor
public class Board {
	@NonNull
	private int boardno;
	private String title;
	private String writer;
	private String content;

}
