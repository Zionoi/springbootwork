package com.study.springboot.service;

import java.util.List;
import java.util.Map;

import com.study.springboot.dto.Board;

public interface BoardService {
	
	public int totalRecord();

	public List<Board> list();

	public Board detailBoard(String boardno);

	public int deleteBoard(String bno);
	
	// 입력값을 맵으로 가져올때
	public int insertBoard(Map<String, String> map);

	public int insertBoard(Board b);

}
