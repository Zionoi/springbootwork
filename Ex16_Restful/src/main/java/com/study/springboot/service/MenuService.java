package com.study.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.domain.Menu;
import com.study.springboot.domain.Taste;
import com.study.springboot.domain.Type;
import com.study.springboot.repository.MenuRepository;

@Service
public class MenuService {
	
	@Autowired
	MenuRepository menuRepository;

	public List<Menu> menuAllList() {
		return menuRepository.findAll();
	}

	public List<Menu> findByType(Type type) {
		return menuRepository.findByType(type);
	}

	public List<Menu> findByTypeAndTaste(Type type, Taste taste) {
		return menuRepository.findByTypeAndTaste(type, taste);
	}

	public Optional<Menu> findById(Long id) {
		return menuRepository.findById(id);
	}

	public Menu insertMenu(Menu menu) {
		return menuRepository.save(menu);
	}

	public Menu updateMenu(Menu menu) {
		//업데이트하려면 검색해서 값을 가져온후 영속성(menu)에 넣어줘야함
		Menu reMenu = menuRepository.findById(menu.getId()).get();	// 그냥 값을 가져오면 옵셔널 객체로 들어오기때문에 get() 메소드로 값 추출
		return menuRepository.save(menu);
	}

	public void deleteMenu(Long id) {
		menuRepository.deleteById(id);
		
	}

}
