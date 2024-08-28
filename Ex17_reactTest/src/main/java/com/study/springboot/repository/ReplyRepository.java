package com.study.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.springboot.domain.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long>{
	
	Reply save(Reply reply);
}
