package com.pos.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "cashier")
public class Cashier implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3896210869697569887L;

	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String nip;
	
	@Column(name="name", nullable=false)
	private String name;
	
	public Cashier() {
	
	}
	
	public Cashier(String nip, String name) {
		this.nip = nip;
		this.name = name;
	}
	public String getNip() {
		return nip;
	}
	public String getName() {
		return name;
	}
	public void setNip(String nip) {
		this.nip = nip;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
