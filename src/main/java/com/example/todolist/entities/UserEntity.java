package com.example.todolist.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="users")
@Data
public class UserEntity {
	
	@Id
	@Column(name="userId")
	private long id;
	
	@Column(name="userName")
	private String username;
	
	@Column(name="userSurname")
	private String usersurname;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column (name ="Phonenumber")
	private long phone;
	
	@Column(name="biografi")
	private String bio;
	
	public UserEntity() {
		
	}

	public UserEntity(long id, String username,String usersurname, String email, String password,long phone,String bio) {
		super();
		this.id = id;
		this.username = username;
		this.usersurname = usersurname;
		this.email = email;
		this.password = password;
		this.phone=phone;
		this.bio=bio;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUserSurname() {
		return usersurname;
	}

	public void setUserSurname(String usersurname) {
		this.usersurname = usersurname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}
	
	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
	
	
	

}
