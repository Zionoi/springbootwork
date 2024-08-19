package com.study.springboot.controller;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.study.springboot.domain.Board;
import com.study.springboot.domain.Reply;
import com.study.springboot.service.BoardService;
import com.study.springboot.service.ReplyService;

import jakarta.servlet.http.HttpSession;


@Controller
@SessionAttributes({"loginUser"})
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	ReplyService replyService;
	
	
	
	/*	 내가 만든 게시글 리스트
	  		@GetMapping("/list")
		    public String list(Model model) {
		        List<Board> pageList = boardService.getBoardList(); // 게시글 목록 가져오기
		        model.addAttribute("boardPage", boardList); // 뷰에 게시글 목록 전달
		        return "board/list"; // board/list.html 뷰를 반환
		    }
		*/
	@GetMapping("/list")
	public String list(@RequestParam(value="nowPage", defaultValue="0") int nowPage, Model model) {
		Page<Board> pageList = boardService.list(PageRequest.of(nowPage, 10, Sort.by(Sort.Direction.DESC, "no")));
		
		//페이지 블록 갯수 몇개보여줄지 정하는 구문
		int pagePerBlock = 5;	// [1][2][3][4][5]
		int endPage = Math.min(pageList.getTotalPages(), nowPage + pagePerBlock);
		
		model.addAttribute("boardPage", pageList);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("endPage", endPage);
		return "board/list";
	}
	
	@GetMapping("insertForm")
	public String insertForm() {
		return "board/insertForm";
	}
	
	@PostMapping("/insert")
	public String insert(Board board) {
		boardService.insert(board);
		return "redirect:list";
	}
	
	@GetMapping("/detailForm")
	public String detailForm(Board b, Model model, HttpSession session) {
		Long no = b.getNo();
		Optional<Board> board = boardService.getBoardList(no);
		model.addAttribute("board", board.get());
		session.setAttribute("boardDetailUrl", "/detailForm?no="+no);
		return "board/detailForm";
	}
	
		@PostMapping("/insertReply")
		public String insertReply(Reply reply, Model model) {
			System.out.println("reply :reply :reply :reply :reply :reply :reply : " + reply);
			System.out.println("reply : " + reply);
			Reply re = replyService.insertReply(reply);
			System.out.println("insert re : " + re);
			model.addAttribute("reply", re);
			Optional<Board> reBoard = boardService.getBoardList(reply.getRefBno());
			model.addAttribute("board", reBoard.get());
			
			return "board/detailForm";
		}
	
	/*	@PostMapping("/insertReply")
		public String insertReply(@RequestParam("content") String replyContent,
						            @RequestParam("writer") String writer,
						            @RequestParam("refBno") Long refBno,
						            Model model) {
			Reply reply = new Reply();
			reply.setContent(replyContent);
			reply.setWriter(writer);
			reply.setRefBno(refBno);
			
			Reply re = replyService.insertReply(reply);
			model.addAttribute("reply", re);
			
			Optional<Board> reBoard = boardService.getBoardList(refBno);
			model.addAttribute("board", reBoard.orElse(null));
			
			return "board/detailForm";
		}*/
	
}
