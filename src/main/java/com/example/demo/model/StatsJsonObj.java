package com.example.demo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonView;

public class StatsJsonObj implements Serializable{
	
	@JsonView(Views.Public.class)
	private String withMutation;
	
	@JsonView(Views.Public.class)
	private String withOutMutation;
	
	@JsonView(Views.Public.class)
	private String ratio;
	
	public StatsJsonObj() {}

	public String getWithMutation() {
		return withMutation;
	}

	public void setWithMutation(String withMutation) {
		this.withMutation = withMutation;
	}

	public String getWithOutMutation() {
		return withOutMutation;
	}

	public void setWithOutMutation(String withOutMutation) {
		this.withOutMutation = withOutMutation;
	}

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	@Override
	public String toString() {
		return "StatsJsonObj [withMutation=" + withMutation + ", withOutMutation=" + withOutMutation + ", ratio="
				+ ratio + "]";
	}
	
	
	
}
