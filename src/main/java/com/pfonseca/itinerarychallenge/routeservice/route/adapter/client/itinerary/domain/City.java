package com.pfonseca.itinerarychallenge.routeservice.route.adapter.client.itinerary.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "City model")
public class City {
	
	@ApiModelProperty(notes = "The database generated city ID")
	private Long id;
	
	@ApiModelProperty(notes = "City name", required=true, allowEmptyValue=false, dataType="String(255)")
	private String name;
	
	public City() {}
	
	public City(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + "]";
	}
	
}
