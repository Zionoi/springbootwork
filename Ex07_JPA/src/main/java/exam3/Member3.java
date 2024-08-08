package exam3;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="JpaMember3")
public class Member3 {
	@Id
	@SequenceGenerator(
			name="mySequence01",
			sequenceName="seq_JpaMem3", // 시퀀스 이름 변경시
			initialValue=1,				// 초기값
			allocationSize=5			// 증가값
			)
	@GeneratedValue(generator="mySequence01") // 시퀀스 이름 초기값 설정시 이렇게
	private Long id;
	
	@Access(AccessType.FIELD)		// 기본값 : 필드(멤버변수)를 통해 데이터에 접근
	private String username;
	
	@Access(AccessType.PROPERTY)	// get/set메소드를 통해 데이터 접근
	private String password;
	
	@Transient 				// 영속성에서 제외 (DB에 안만들어짐)
	private long timestamp1;	//방법1 어노테이션
	transient private long timestamp2;		//방법2	지시자를 이용
	
	public Member3() {
		super();
	}

	public Member3(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
