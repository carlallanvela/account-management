package com.cvela.accountmanagement.account;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Account Bean.
 * @author Carl Allan Vela
 *
 */
@ApiModel(description="All details about the Account.")
@Entity
public class Account {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min = 2, message = "Name should have at least 2 characters.")
	@ApiModelProperty(notes="Name should have at least 2 characters. ")
	private String name;
	
	@Past(message = "Date should be in the past.")
	@ApiModelProperty(notes="Transaction date should be in the past. ")
	private Date transactionDate;
	
	@OneToMany(mappedBy="account")
	private List<Transaction> transactions;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getTransactionDate() {
		return transactionDate;
	}
	
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", transactionDate=" + transactionDate + "]";
	}
	
	protected Account() { }
	
	public Account(Integer id, String name, Date transactionDate) {
		super();
		this.id = id;
		this.name = name;
		this.transactionDate = transactionDate;
	}
}
