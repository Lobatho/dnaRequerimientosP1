package com.example.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Dna;

@Repository("repository")
public interface DnaRepo extends JpaRepository<Dna, Serializable>{
	
	public abstract List<Dna> findByMutation(boolean mutation);
	
	public abstract Dna findByNameAndId(String name, int id);
	
	
	
}
