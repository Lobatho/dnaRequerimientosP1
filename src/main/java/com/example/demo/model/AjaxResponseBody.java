package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

public class AjaxResponseBody {

	@JsonView(Views.Public.class)
	String msg;
	
	@JsonView(Views.Public.class)
	String code;
	
	@JsonView(Views.Public.class)
	List<MDna> result;
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code=code;
	}
	
	public List<MDna> getResult(){
		return result;
	}
	
	public void setResult(List<MDna> result) {
		this.result = result;
	}
	
	@Override
	public String toString() {
		return "AjaxResponseBody [msg="+msg+", code="+code+", result="+result+"]";
	}
}
