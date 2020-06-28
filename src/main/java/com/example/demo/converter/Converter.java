package com.example.demo.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Dna;
import com.example.demo.model.MDna;

@Component("converter")
public class Converter {
public List<MDna> convertList(List<Dna> dnaList){
	List<MDna> mdnas = new ArrayList<>();
	for(Dna dna : dnaList) {
		mdnas.add(new MDna(dna));
	}
	return mdnas;
}
}
