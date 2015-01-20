package ar.edu.unlp.info.model;

import java.util.Date;

public class Investment extends PersistentEntity {

	private Double amount;
	private Date date;
	
	public Investment() {}
	
	public Investment(Double amount, Date date) {
		super();
		this.amount = amount;
		this.date = date;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
