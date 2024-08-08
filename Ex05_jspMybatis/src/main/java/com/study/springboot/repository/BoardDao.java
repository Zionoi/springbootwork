package com.study.springboot.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.dto.Board;

//매퍼라고 어노테이션을 달아준다. 
@Mapper
public interface BoardDao {
	public int totalRecord();
	public List<Board> list();
	public Board detailBoard(String boardno);
	public int deleteBoard(String bno);
	public int insertBoard(Map<String, String> map);
	public int insertBoard(Board b);
	
}
