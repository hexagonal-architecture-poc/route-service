package com.pfonseca.itinerarychallenge.routeservice.route.adapter.client.itinerary.domain;

import java.time.LocalTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Itinerary between two cities")
public class Itinerary {

	@ApiModelProperty(notes = "The database generated itinerary ID")
	private Long id;
	
	@ApiModelProperty(notes = "Origin city", required=true)
	private City origin;
	
	@ApiModelProperty(notes = "Destiny city", required=true)
	private City destiny;
	
	@ApiModelProperty(notes = "Departure time. Format: \"HH:mm:ss\"", required=true)
	private LocalTime departureTime;
	
	@ApiModelProperty(notes = "Arrival time. Format: \"HH:mm:ss\"", required=true)
	private LocalTime arrivalTime;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public City getOrigin() {
		return origin;
	}
	public void setOrigin(City origin) {
		this.origin = origin;
	}
	public City getDestiny() {
		return destiny;
	}
	public void setDestiny(City destiny) {
		this.destiny = destiny;
	}
	public LocalTime getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}
	public LocalTime getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	@Override
	public String toString() {
		return "Itinerary [id=" + id + ", origin=" + origin + ", destiny=" + destiny + ", departureTime="
				+ departureTime + ", arrivalTime=" + arrivalTime + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Itinerary other = (Itinerary) obj;
		if (id == null) {
			return other.id == null;
		} else return id.equals(other.id);
	}
	
}
