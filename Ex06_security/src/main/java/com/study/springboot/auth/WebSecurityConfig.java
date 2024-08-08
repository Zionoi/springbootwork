package com.study.springboot.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.DispatcherType;




@Configuration
public class WebSecurityConfig {
	@Autowired
	AuthenticationFailureHandler authenticationFailureHandler;
	
	@Bean		//시큐리티 설정할수있게 해주는 스프링부트 자체 클래스 임폴트해서 사용 
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		//.csrf()는 해킹 대비.	사이트간에 통신중 데이터를 가로채서 내용을 바꾸고 다시보내는 공격을 막아주는 메소드// 6.1버전부터 람다식으로 써야하게 바뀜
		// 토큰을 발행해서 인증을 해야 통신허용// 지금은 disable로하지만 실제 사용할땐 토큰 만들고 설정도 따로 해줘야함
		
		//.cors()는 접근권한 설정
		
		http.csrf((csrf) -> csrf.disable())
			.cors((cors) -> cors.disable())
			.authorizeHttpRequests(request -> request
					.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll() // 포워딩 되는 문서 아무나 들어갈수있게 해줌
					.requestMatchers("/").permitAll()							// 루트도 아무나 들어올수있게
					.requestMatchers("/css/**", "/js/**", "/img/**").permitAll()// css js img도 아무나 볼수있게
					.requestMatchers("/guest/**").permitAll()					// guest파일밑에있는 문서들 전부 아무나 볼수있게
					.requestMatchers("/member/**").hasAnyRole("USER", "ADMIN")	// member 파일밑에 있는 문서는 USER, ADMIN만 볼수있게 권한설정(한명이상일땐 hasAnyRole()을 사용
					.requestMatchers("/admin/**").hasRole("ADMIN")				// admin 파일밑에 문서들은 ADMIN만 볼수있게 (한명만 권한줄땐 hasRole()을 사용)
					// .anyRequest().authenticated() :
					// 위 리소스를 설정 후 그외 나머지 리소스들은 무조건 인증을 완료해야 접근이 가능하다는 의미.
					.anyRequest().authenticated()
			);
		/*	// 스프링부트 자체 로그인 로그아웃 폼
			http.formLogin((formLogin) -> formLogin.permitAll());
			http.logout((logout) -> logout.permitAll());
		*/	
		// 아래는 직접 만든 로그인 폼
		http.formLogin((formLogin) -> formLogin
				.loginPage("/loginForm")			// 로그인 페이지 url을 /와 함께 적어주기 default : /login
				.loginProcessingUrl("/login_check") // form에 action태그에 넣었던 url을 / 와 함께 적어주면됨.
				//.failureUrl("/loginError") 		// 로그인 실패시 보내줄 에러페이지 url
				//.failureUrl("/loginForm?error") 	// 윗줄은 에러페이지로 보내는거고 이건 로그인폼으로 error값 담아서 되돌려보내는것
				.failureHandler(authenticationFailureHandler)  // 로그인 실패시 만들어놓은 핸들러로 보냄. 위쪽에 임폴트 해놓음 AuthenticationFailureHandler는 CustomFailureHandler의 부모 인터페이스
				.usernameParameter("username")	// 로그인 폼에 유저 아이디 인풋 이름 적어주면됨. 파라미터 디폴트(j_username)
				.passwordParameter("pwd")		// 로그인 폼에 유저 패스워드 인풋 name 적어주기. 파라키터 디폴트(j_password)
				.permitAll());
		
		
		// 로그아웃은 기본폼 그대로 쓸거임
		http.logout((logout) -> logout.permitAll());
		return http.build();
	}
	
	
	// db에 넣지 않고 메모리에 저장
	@Bean
	public UserDetailsService users() {
		UserDetails user = User.builder()
				.username("user")
				.password(passwordEncoder().encode("1234"))
				.roles("USER")
				.build();
		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("1234"))
				.roles("USER", "ADMIN")
				.build();
				// 메모리 안에다 넣어주려면 new InMemoryUserDetailsManager() 객체사용 스트링부트 자체 클래스임
		return new InMemoryUserDetailsManager(user, admin);
	}
	
	// 패스워드 암호화
	// 아래 메소드는 static으로 만들어져있어서 클래스위에 @Bean을 안붙여도 사용할 수 있다.
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
//	
//	@Bean
//    public AuthenticationFailureHandler customFailureHandler() {
//        return new CustomFailureHandler();
//    }
}
