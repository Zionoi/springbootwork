package exam4;

import java.time.LocalDate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class MemberSelect {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaEx01");
		EntityManager em = emf.createEntityManager();
	
		
		try {
			em.getTransaction().begin();
				
			// 자료 검색후 해당 빈에 담음
			Member4 user = em.find(Member4.class, "kyh028@naver.com");
			
			if(user != null) {
				System.out.println("이름 :  "+ user.getName());
				System.out.println("가입날짜 : " + user.getCreateDate());
			}else {
				System.out.println("존재하지 않습니다");
			}
			
			
			em.getTransaction().commit();
			
		}catch(Exception e){
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		em.close();
		emf.close();

	}

}
