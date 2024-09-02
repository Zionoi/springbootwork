package com.study.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.domain.Cart;
import com.study.springboot.repository.CartRepository;

@Service
public class CartService {
	
	@Autowired
	CartRepository cartRepository;

	public Cart addcart(Cart cart) {
		return cartRepository.save(cart);
	}

	public List<Cart> getCart(String memId) {
		
		return cartRepository.findByMemIdOrderById(memId); //memId에 해당하는 리스트만 가져오기// 아이디 오름차순으로
	}

}
