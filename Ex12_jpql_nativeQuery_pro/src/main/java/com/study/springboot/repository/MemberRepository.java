package com.study.springboot.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.study.springboot.domain.Member;
/*
 * JPQL(Java Persistence Query Language)
   : JPA의 query Methods만으로는 조회가 불가능한 경우 
     JPQL을 이용하여 SQL과 비슷한 형태의 쿼리를 작성하여 조회
     
 * nativeQuery
   : SQL문을 직접 정의하여 사용하는 방식
 */
@Repository													// Long은 @Id가 붙은 필드의 자료형
public interface MemberRepository extends JpaRepository<Member, Long> {
	
	// JPQL 쿼리 : from뒤에는 영속성에 있는 엔티티명(DB테이블이 아님. 영속성 테이블명은 반드시 대문자로)
	// 엔티티명대문자 => 별칭입력 => 모든컬럼 가져올시 *사용불가 별칭 써야함 => 선택적으로 가져오려면 select m.name, m.age 이런식으로
	// 넘어온 정보랑 비교시 (@Param("변수명") 넘길때 설정한 변수명과 자료형)으로 값 받은후 like :변수명. 콜론뒤에 변수명 띄어쓰기없이 입력 ':' == '=' 이라고 생각하면 됨.
	@Query("select m from JPAPAGING m where m.name like :name order by m.id desc")
	List<Member> findByNameLike(@Param("name") String search);
	
	@Query("select m from JPAPAGING m where m.name like :name")
	List<Member> findMembers(@Param("name") String search, Sort sort);
	
	@Query("select m from JPAPAGING m where m.name like :name")
	Page<Member> findMembers(@Param("name") String search, Pageable pageable);
	
	// 일반 sqp쿼리 : 테이블명 등 대소문자 가리지 않음
	@Query(value="select * from jpapaging where name like :name order by id desc", nativeQuery=true)
	List<Member> findMembersNative(@Param("name") String search);
	
			
}








