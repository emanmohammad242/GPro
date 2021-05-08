package com.example.Farha;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table

public class wallet {

	@Id
	private int idWallet ;
	@Column
	private double balance ;

	public wallet(int idWallet ,double balance) {
		super();
		this.idWallet=idWallet;
		this.balance = balance;
	}

	
	public int getIdWallet() {
		return idWallet;
	}


	public void setIdWallet(int idWallet) {
		this.idWallet = idWallet;
	}


	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "wallet [balance=" + balance + "]";
	}
	
	public void withDrawal(double amount)
	{
	}
	
	public void deposit(double amount)
	{
	}
	
	public void pay(int id , double amount)
	{
	}
}
