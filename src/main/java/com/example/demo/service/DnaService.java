package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.converter.Converter;
import com.example.demo.entity.Dna;
import com.example.demo.model.AjaxResponseBody;
import com.example.demo.model.AjaxResponseBodyObj;
import com.example.demo.model.MDna;
import com.example.demo.model.StatsJsonObj;
import com.example.demo.repository.DnaRepo;

@Service("service")
public class DnaService {
	@Autowired
	@Qualifier("repository")
	private DnaRepo repository;
	
	@Autowired
	@Qualifier("converter")
	private Converter converter;
	
	
	public List<MDna> getAllDnas(){
		
		return converter.convertList(repository.findAll());
	}
	
	public AjaxResponseBody getAll() {
		AjaxResponseBody ajax = new AjaxResponseBody();
		List<MDna> dnas = converter.convertList(repository.findAll());
		ajax.setCode("200");
		ajax.setMsg("success");
		ajax.setResult(dnas);
		return ajax;
	}
	
public AjaxResponseBody getMutation(String mutation) {
	AjaxResponseBody ajax = new AjaxResponseBody();
	List<MDna> dnas = converter.convertList(repository.findByMutation(Boolean.valueOf(mutation)));
	ajax.setCode("200");
	ajax.setMsg("success");
	ajax.setResult(dnas);
	return ajax;
	}
	
	
	
	public boolean UpdateDna(Dna dna) {
		try {
			repository.save(dna);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public boolean deleteDna(String name, int id) {
		try {
			Dna dna = repository.findByNameAndId(name, id);
			repository.delete(dna);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public AjaxResponseBodyObj getMutationStats() {
		AjaxResponseBodyObj ajax = new AjaxResponseBodyObj();
		ajax.setCode("200");
		ajax.setMsg("success");
		double dnasTrue = Double.valueOf(repository.findByMutation(true).size());
		double dnasFalse = Double.valueOf(repository.findByMutation(false).size());
		List<Object> objectList = new ArrayList<Object>();
		StatsJsonObj json = new StatsJsonObj();
		json.setWithMutation(String.valueOf(dnasTrue));
		json.setWithOutMutation(String.valueOf(dnasFalse));
		json.setRatio(String.valueOf(dnasTrue/dnasFalse));
		objectList.add(json);
		ajax.setResult(objectList);
		return ajax;
	}
	


	
	public boolean hasMutation(String[] dna) {
		boolean result = false;
		
		if(validateDna(dna)) {
			System.out.println("Todo ok");
			int mutationCount = 0;
			int TCount = 0;
			int ACount = 0;
			int GCount = 0;
			int CCount = 0;
			String[][] dnaMatrix = createMatrix(dna); 
			
			for (int x=0; x < dnaMatrix.length; x++){
		        for (int y=0; y < dnaMatrix[x].length; y++) {
		        	switch(dnaMatrix[x][y]) {
		        	case "T":
		        		TCount+=1;
		        		break;
		        	case "A":
		        		ACount+=1;
		        		break;
		        	case "G":
		        		GCount+=1;
		        		break;
		        	case "C":
		        		CCount+=1;
		        		break;
		        		default:
		        		
		        	}
		        }
		        
		        if(TCount == 4||ACount == 4||GCount == 4||CCount == 4) {
					mutationCount+=1;
				}
				TCount = 0;
				ACount = 0;
				GCount = 0;
				CCount = 0;        
			}
			System.out.println("Horizontal: "+mutationCount);
			if(mutationCount>1) {
				result = true;
			}else {
				mutationCount = 0;
				for (int y=0; y < dnaMatrix[0].length; y++){
						for (int x=0; x < dnaMatrix.length; x++) {
				        	switch(dnaMatrix[x][y]) {
				        	case "T":
				        		TCount+=1;
				        		break;
				        	case "A":
				        		ACount+=1;
				        		break;
				        	case "G":
				        		GCount+=1;
				        		break;
				        	case "C":
				        		CCount+=1;
				        		break;
				        		default:
				        	}	
				        	
				        }
						
					if(TCount == 4||ACount == 4||GCount == 4||CCount == 4) {
						mutationCount+=1;
					} 
					TCount = 0;
					ACount = 0;
					GCount = 0;
					CCount = 0;
			        
			               
				}
				System.out.println("Vertical: "+mutationCount);
				if(mutationCount>1) {
					result = true;
				}else {
					mutationCount = 0;
					//diagonal sentido1
					//diagonal superior
					for (var i=0;i<dnaMatrix.length;i++) {
						for (var j=0;j<=i;j++) { 
							switch(dnaMatrix[i-j][j]) {
				        	case "T":
				        		TCount+=1;
				        		break;
				        	case "A":
				        		ACount+=1;
				        		break;
				        	case "G":
				        		GCount+=1;
				        		break;
				        	case "C":
				        		CCount+=1;
				        		break;
				        		default:
				        	}
							
							
						}
						
						if(TCount == 4||ACount == 4||GCount == 4||CCount == 4) {
							mutationCount+=1;
						} 
						TCount = 0;
						ACount = 0;
						GCount = 0;
						CCount = 0;
					}
					
					if(mutationCount>1) {
						result = true;
					}else {
						//diagonal inferior
						for (var i=0;i<dnaMatrix.length;i++) {
						    for (var j=0;j<dnaMatrix.length-i-1;j++) { 
						    	switch(dnaMatrix[dnaMatrix.length-j-1][j+i+1]) {
					        	case "T":
					        		TCount+=1;
					        		break;
					        	case "A":
					        		ACount+=1;
					        		break;
					        	case "G":
					        		GCount+=1;
					        		break;
					        	case "C":
					        		CCount+=1;
					        		break;
					        		default:
					        	}
						    	
						    }
						    
						    if(TCount == 4||ACount == 4||GCount == 4||CCount == 4) {
								mutationCount+=1;
							} 
							TCount = 0;
							ACount = 0;
							GCount = 0;
							CCount = 0;
						}
						System.out.println("Diagonal: "+mutationCount);
						if(mutationCount>1) {
							result = true;
						} else {
							
							mutationCount = 0;
							//diagonalInvertida
							
							
							
							
						}
						
						
					}
					
				}
			
			}
			
		}else {
			System.out.println("Hay caracteres invalidos en el ADN, por favor revise");
			result = (Boolean) null;
		}
		
		if(result)
			System.out.println("Hay Mutacion");
		else
			System.out.println("No hay mutacion");
		
		return result;
	}
	
public static String[][] createMatrix(String[] dna) {
		
		
		
		List<String[]> mtrix = new ArrayList(0);
		
		for(String dnaData : dna) {
			String[] vect = dnaData.split("");
			mtrix.add(vect);
			
		}
		
		String[][] dnaMatrix = new String[mtrix.size()][];
		for(int i=0; i <mtrix.size(); i++) {
			String[] row = mtrix.get(i);
			dnaMatrix[i]=row;
		}
		
		
		return dnaMatrix;
	}
	
	public static boolean validateDna(String[] dna) {
		boolean validate = true;
		Pattern pat = Pattern.compile("(T|A|G|C)+");
		for (String dnaValue : dna) {
			Matcher mat = pat.matcher(dnaValue);
			if (!mat.matches()) {
		         validate=false;
		         break;
		     } 
		}
		
		
		return validate;
	}
	
	public boolean registerNew(String name) {
		final String dnasChar = "ATCG";
		StringBuilder builder = new StringBuilder();
		int size = 0;
		int arraySize = 0;
		
		ArrayList<String> dnaRandom = new ArrayList<String>();
		while(arraySize<6) {
			while(size < 6) {
				int character = (int) (Math.random()*dnasChar.length());
				builder.append(dnasChar.charAt(character));
				size+=1;
			}
			dnaRandom.add(builder.toString());
			arraySize+=1;
			
		}
		
		String[] dnaGenerated = dnaRandom.toArray(new String[dnaRandom.size()]);
		
		Dna dnaNew = new Dna();
		dnaNew.setId(100);
		dnaNew.setDna(dnaRandom.toString());
		dnaNew.setMutation(hasMutation(dnaGenerated));
		dnaNew.setName(name);
		
		return registerDna(dnaNew);
	}
	
	public boolean registerDna(Dna dna) {
		try {
			repository.save(dna);
			return true;
		}catch(Exception e) {
			System.out.println("Error register: "+e.toString());
			return false;
		}
	}
	

}
