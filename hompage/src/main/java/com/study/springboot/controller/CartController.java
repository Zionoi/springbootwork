package com.study.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.springboot.domain.Cart;
import com.study.springboot.service.CartService;


@RestController
@RequestMapping("/react")
public class CartController {
	
	@Autowired
	CartService cartService;
	
	//장바구니에 상품 추가
	@PostMapping("/addCart")
	public Cart addCart(@RequestBody Cart cart) {
		cart.setMemId("user01");		
		return cartService.addcart(cart);
	}
	
	//장바구니 목록 불러오기
	@GetMapping("/getCart")
	public List<Cart> getCart(){
		List<Cart> list = cartService.getCart("user01");
		return list;
	}
	
}
