package com.study.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.study.springboot.domain.Board;
import com.study.springboot.domain.Member;
import com.study.springboot.repository.BoardRepository;

@Service
public class BoardService {
	
	@Autowired
	BoardRepository boardRepository;

	public Board insert(Board board) {
		return boardRepository.save(board);
		
	}
	
	
	//내가 만든 게시글 가져오는 메소드
	public List<Board> getBoardList() {	
		return boardRepository.findAll();  // 모든 게시글을 가져오는 메소드
	}

	// 교수님 버전
	public Page<Board> list(PageRequest of) {
		return boardRepository.findAll(of);
	}


	public Optional<Board> getBoardList(Long no) {
		return boardRepository.findById(no);
	}
	
	
	

}
