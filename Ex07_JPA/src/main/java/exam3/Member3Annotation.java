package exam3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Member3Annotation {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaEx01");
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
	
		
		try {
			transaction.begin();
			Member3 user = new Member3("김요한", "1234");
			entityManager.persist(user);	// 영속성에 위에 user값 넣음
			
			
			transaction.commit();
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
		}finally {
			entityManager.close();
			emf.close();
		}
	}

}
