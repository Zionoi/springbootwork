package com.study.springboot.auth;


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

import jakarta.servlet.DispatcherType;




@Configuration
public class WebSecurityConfig {
			
	@Bean		//시큐리티 설정할수있게 해주는 스프링부트 자체 클래스 임폴트해서 사용 
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		//.csrf()는 	사이트간에 통신중 데이터를 가로채서 내용을 바꾸고 다시보내는 공격을 막아주는 메소드// 6.1버전부터 람다식으로 써야하게 바뀜
		// 토큰을 발행해서 인증을 해야 통신허용// 지금은 disable로하지만 실제 사용할땐 토큰 만들고 설정도 따로 해줘야함
		
		http.csrf((csrf) -> csrf.disable())
			.cors((cors) -> cors.disable())
			.authorizeHttpRequests(request -> request
					.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
					.requestMatchers("/").permitAll()
					.requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
					.requestMatchers("/guest/**").permitAll()
					.requestMatchers("/member/**").hasAnyRole("USER", "ADMIN")
					.requestMatchers("/admin/**").hasRole("ADMIN")
					// .anyRequest().authenticated() :
					// 위 리소스를 설정 후 그외 나머지 리소스들은 무조건 인증을 완료해야 접근이 가능하다는 의미.
					.anyRequest().authenticated()
			);
			http.formLogin((formLogin) -> formLogin.permitAll());
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
		return new InMemoryUserDetailsManager(user, admin);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
