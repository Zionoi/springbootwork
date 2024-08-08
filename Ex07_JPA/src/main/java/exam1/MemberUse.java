package exam1;

import java.time.LocalDate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class MemberUse {

	public static void main(String[] args) {
								//여기에 Persistence.xml에 ersistence-unit name="" 에 들어있는값 그대로 넣어줘야 설정해놓은 값들이 적용됨.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaEx01");
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		try {
		// transaction :  begin 부터 쌓아놨다가 한번에 cummit 하라는 문구
		transaction.begin();
		Member1 user = new Member1("홍길동", LocalDate.now());
		
		//.persist는 영속성으로 객체에 데이터 입력(메모리에 insert해주는 부분) 즉 db에 바로가는게 아니라 메모리에 1차적으로 모아놨다가 커밋시 DB로 전송되는것
		entityManager.persist(user);
		
		transaction.commit();
		} catch(Exception e) {
			e.printStackTrace();
			// 오류시 커밋하지말고 롤백
			transaction.rollback();
		}finally {
			entityManager.close();
			emf.close();
		}
	}
	
	
}
