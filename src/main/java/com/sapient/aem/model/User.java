 package com.sapient.aem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
	private Integer userId;
	private String userName;
	private String password;
	private Role role;
	
	public User(String username, String password, Role role) {
		super();
		this.userName = username;
		this.password = password;
		this.role = role;
	}
	
	
}
