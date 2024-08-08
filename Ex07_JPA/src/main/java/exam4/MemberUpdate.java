package exam4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class MemberUpdate {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaEx01");
		EntityManager em = emf.createEntityManager();


		try {
			em.getTransaction().begin();
				
			// 자료 검색후 해당 빈에 담음
			Member4 user = em.find(Member4.class, "kyh028@naver.com");
			
			if(user == null) {
				System.out.println("존재하지 않습니다");
				return;
			}
			
			user.changeName("바꾼이름"); // 빈클래스에 메소드 직접만듬
			
			em.getTransaction().commit();
			System.out.println("이름을 변경하였습니다");
			
		}catch(Exception e){
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		em.close();
		emf.close();

	

	}

}
