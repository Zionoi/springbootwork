package com.study.springboot.domain;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Member {
	@Id
	private String userid;
	private String pass;
	private String nickname;
	private String phone;
	private String birthday;
	private String email;
	private String address;
	private String gender;
	private String createDate;
	
}
