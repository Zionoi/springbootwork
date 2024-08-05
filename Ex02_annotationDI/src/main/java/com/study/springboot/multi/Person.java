package com.study.springboot.multi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// 어노테이션으로 의존성 주입
//help -> 이클립스 마켓플레이스에서 java and web 검색후 다운로드 -> 밑에 @Component 달아주면 해당 객체를 빈으로 등록할수있다.


// 이름을 명시하지 않으면 빈의 이름은 소문자로 등록(person등록)
@Component
public class Person {	
	@Value("홍길동")
	private String name;
	
	@Value("홍홍길길동동")
	private String nickname;
	
	@Autowired //객체일경우엔 Autowired 임폴트후 사용 자동으로 해당객체를 찾음 *해당객체가 여러개일경우엔 @Qualifier("객체이름")으로 명시해줘야 한다.
	@Qualifier("printerA") //객체이름 명시하지않았을땐 앞글자가 소문자로들어가서 명시할때도 소문자로해줘야함.
	private Printer printer;
	public Person() {
		super();
	}
	public Person(String name, String nickname, Printer printer) {
		super();
		this.name = name;
		this.nickname = nickname;
		this.printer = printer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Printer getPrinter() {
		return printer;
	}
	public void setPrinter(Printer printer) {
		this.printer = printer;
	}
	
	public void print() {
		printer.print("Hello " + name + " : " + nickname);
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", nickname=" + nickname + ", printer=" + printer + "]";
	}
	
}
