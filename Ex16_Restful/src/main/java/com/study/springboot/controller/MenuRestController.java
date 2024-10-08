package com.study.springboot.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.springboot.domain.Menu;
import com.study.springboot.domain.Taste;
import com.study.springboot.domain.Type;
import com.study.springboot.service.MenuService;

@RestController
@RequestMapping("/menu") 	// /menu 로 들어오는건 다 이쪽으로 들어오게 세팅
public class MenuRestController {
	
	@Autowired
	MenuService menuService;
	
	@GetMapping
	public List<Menu> menuAllList(){
		return menuService.menuAllList();
	}
	

	/*
	 	/menu/type/KR
	 	/menu/type/CH
	 	/menu/type/JP
	 */
	@GetMapping("/type/{type}")
	public List<Menu> findByType(@PathVariable(name="type") Type type){
		return menuService.findByType(type);
	}
		
	// /menu/type/${type}/taste/${taste}
	@GetMapping("/type/{type}/taste/{taste}")
	public List<Menu> findByTypeAndTaste(@PathVariable(name="type") Type type,
										 @PathVariable(name="taste") Taste taste){
		return menuService.findByTypeAndTaste(type, taste);
		
	}
	
	/*	@GetMapping("/{id}")
		public Menu findById(@PathVariable(name="id") Long id) {
			return menuService.findById(id).get();
		}*/
		// 정상조회가 안되면(id가 없을 때) 500오류 발생
		// 500 : 서버측 오류 이나
		// 이경우 사용자가 없는 id를 잘못넣어서 발생된 오류
	/*
		지금까지는 상태코드를 지정하기 위해 HttpServletResponse의 setstatus()와 sendError()를 사용
		문제점은 에러시, json이 아닌 view(html, jsp)로 결과를 응답함.
		오류를 json으로 응답하기 위해 ResponseEntity 사용해야한다.
		
		- restFul하게 만들때는 요구한 자원이 없을 때 404오류 반환(클라이언트측 오류<없는 id를 넣었기 때문>)
		null일 때와 null이 아닐때를 나누어 처리가 필요
		
		* ResponseEntity : 결과 데이터와 HTTP상태코드와 오류코드까지 직접 제어할 수 있는 클래스
			- status : 응답에 대한 status코드
			- header : header(요청/응답)에 대한 요구사항
			- body : 메시지 body에 작성될 내용(요구사항의 내용)
		
		* ResponseEntity 사용법
		 	- status와 body를 이옹
		 	  ResponseEntity.status(상태코드).body(객체)
		 	  
		 	  + 상태코드 HttpStatus에 정의된 값 이용
		 	  	document : https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/HttpStatus.html
		 
		 	  + 상태코드 ok와 body를 한번에 사용
		 	  	ResponseEntity.ok(member)
		 	  
		 	  + body가 없을 때, build()사용하여 상태코드를 보내줄 때
		 	   ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		 	   
		 	  + body가 없고, status를 대신하여 사용하는 메서드
		 	  	noContent() : 204
		 	  	badRequest() : 400
		 	  	notFound() : 404
		
	*/
	@GetMapping("/{id}")
	public ResponseEntity<Menu> findById(@PathVariable(name="id") Long id) {
		Optional<Menu> menu = menuService.findById(id);
		System.out.println("Rcontroller id: "+menu.get());
		if(menu.isPresent()) {
			return ResponseEntity.ok().body(menu.get());	// ok : 200
		}else {
			return ResponseEntity.notFound().build();		// notFound() : 404
		}
	}
	
	@PostMapping()
	public ResponseEntity<?> insertMenu(@RequestBody Menu menu){
		Menu reMenu = menuService.insertMenu(menu);
		return ResponseEntity.created(URI.create("/menu/" + reMenu.getId())).build();		// 이 메소드는 uri를 넘겨줘야함 지금은 검색해서 가져온값의 id를 같이 넘겨줌
	}
	
	@PutMapping()
	public ResponseEntity<?> updateMenu(@RequestBody Menu menu){
		Menu reMenu = menuService.updateMenu(menu);
		return ResponseEntity.ok(reMenu);		// ok이면 상태코드는 202		
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMenu(@PathVariable(name="id") Long id){
		menuService.deleteMenu(id);
		return ResponseEntity.noContent().build(); 	// noContent :204
	}
	
	
}
