package com.example.Farha;

import javax.persistence.*;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table
public class person {
    @Column(name="Name")
	private String name ;
    @Column(name="Email")
	private String  email ;
	@Id
	private int id ;
	@Column(name="Password")
	private String password ;
	@Column(name="LoginStatuse")
	private String loginStatues ;
	@Column(name="Address")
	private String address ;
	@Column
	private int phoneNumber ;
	
	public person(String name, String email, int id, String password, String loginStatues, String address,
			int phoneNumber) {
		super();
		this.name = name;
		this.email = email;
		this.id = id;
		this.password = password;
		this.loginStatues = loginStatues;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLoginStatues() {
		return loginStatues;
	}
	public void setLoginStatues(String loginStatues) {
		this.loginStatues = loginStatues;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return "person [name=" + name + ", email=" + email + ", id=" + id + ", password=" + password + ", loginStatues="
				+ loginStatues + ", address=" + address + ", phoneNumber=" + phoneNumber + "]";
	}
	
	
	public boolean VerifyLogin()
	{
		return false ;
	}
	
}
