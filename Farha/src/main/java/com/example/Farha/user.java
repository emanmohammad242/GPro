package com.example.Farha;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table

public class user {
	
	@OneToOne
	wallet Wallet ;
	@Id
	int idUser ;

	public user(int idUser , wallet wallet) {
		super();
		this.idUser=idUser ;
		Wallet = wallet;
	}
	
	

	public wallet getWallet() {
		return Wallet;
	}



	public void setWallet(wallet wallet) {
		Wallet = wallet;
	}



	public int getIdUser() {
		return idUser;
	}



	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}



	@Override
	public String toString() {
		return "user [Wallet=" + Wallet + "]";
	}
	
	

}
