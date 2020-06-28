package com.example.demo.model;

import com.example.demo.entity.Dna;

public class MDna {
	
	private int id;
	private String name;
	private Boolean mutation;
	private String dna;
	
	public MDna() {
		
	}
	
	public MDna (Dna dna) {
		this.id = dna.getId();
		this.name = dna.getName();
		this.dna = dna.getDna();
		this.mutation = dna.getMutation();
	}
	
	public MDna(int id, String name, String dna, Boolean mutation) {
		super();
		this.id = id;
		this.name = name;
		this.dna = dna;
		this.mutation = mutation;
	}
	
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

