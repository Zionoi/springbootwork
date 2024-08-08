package exam1;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
/*
	@Entity 해당 클래스가 JPA의 엔티티임을 의미(테이블과 매핑되는 JPA)	
	@Id : 식별자 => primary key
	@GeneratedValue : 자동 생성 sequence
	@Column : 테이블과 매핑된 컬럼명 지정. 이 어노테이션이 없으면 필드명==컬럼명
*/
//@Entity : 클래스 이름 그대로 테이블 생성하라는 뜻 테이블 명을 바꾸고 싶을땐 바로아래@Table(name="바꿀이름:) 달아주면 된다
@Entity
@Table(name="JpaMember1")
public class Member1 {
	@Id // @Id는 프라이머리키라는 의미임 아래변수명 다르게해도 상관없음 햇갈리지 말기
	@GeneratedValue 
	private Long id;
	// int는 기본자료형 => 별도의 초기화과정이 없을시 0으로 초기화되므로 null값 비교를 할 수 없다
	// Long은 wrapper 래퍼클래스 => null값 비료를 할 수 없다 (ex. id != null) 추후 매핑시 이런 비교를 하기 위해 래퍼클래스 사용
	private String username;
	// LocalDate : 년 월 일
	// LocalDateTime : 년월일시간
	@Column(name="create_date") // 컬럼명 변경시
	private LocalDate createDate;
	
	public Member1() {
		super();
	}

	public Member1(String username, LocalDate createDate) {
		super();
		this.username = username;
		this.createDate = createDate;
	}
	
	
	
	
	
	
}
