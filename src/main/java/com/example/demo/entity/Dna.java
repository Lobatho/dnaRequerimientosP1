package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="DNA")
@Entity
public class Dna implements Serializable{
	
	public Dna() {
		
	}
	
	public Dna(int id, String name, String dna, Boolean mutation) {
		super();
		this.id = id;
		this.name = name;
		this.mutation = mutation;
		this.dna = dna;
	}
	
	@GeneratedValue
	@Id
	@Column(name="ID_DNA")
	private int id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="MUTATION")
	private Boolean mutation;
	
	@Column(name="DNA")
	private String dna;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getMutation() {
		return mutation;
	}

	public void setMutation(Boolean mutation) {
		this.mutation = mutation;
	}

	public String getDna() {
		return dna;
	}

	public void setDna(String dna) {
		this.dna = dna;
	}
	
	

	
	
	

}

