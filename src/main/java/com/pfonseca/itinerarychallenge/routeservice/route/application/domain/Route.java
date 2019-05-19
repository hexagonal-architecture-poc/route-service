package com.pfonseca.itinerarychallenge.routeservice.route.application.domain;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pfonseca.itinerarychallenge.routeservice.route.adapter.client.itinerary.domain.Itinerary;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Route based on a list of itineraries")
public class Route {

	@ApiModelProperty(notes = "Origin city id")
	private Long originId;
	
	@ApiModelProperty(notes = "Origin city id")
	private Long destinyId;
	
	@ApiModelProperty(notes = "Itinerary list", required=true)
	private List<Itinerary> itineraries = new ArrayList<>();
	
	private boolean invalid=false;
	
	public Route(Long originId, Long destinyId) {
		this.originId = originId;
		this.destinyId = destinyId;
	}
	
	public Route(Route route) {
		this.originId = route.getOriginId();
		this.destinyId = route.getDestinyId();
		this.itineraries = new ArrayList<>(route.getItineraries());
	}
	
	@JsonIgnore
	public boolean isCompleted() {
		
		if(CollectionUtils.isEmpty(itineraries))
			return false;
		
		return itineraries.get(itineraries.size()-1).getDestiny().getId().equals(destinyId);
	}
	
	public boolean addItinerary(Itinerary itinerary) {
		this.itineraries.add(itinerary);
		return true;
	}
	
	public List<Itinerary> getItineraries() {
		return itineraries;
	}

	@ApiModelProperty(notes = "Time (in minutes) from origin city to destiny city")
	public Long getTotalTime() {
		
		if(CollectionUtils.isNotEmpty(this.itineraries)) {
			
			LocalTime startTime = this.itineraries.get(0).getDepartureTime();
			LocalTime finishTime = this.itineraries.get(this.itineraries.size()-1).getArrivalTime();
			
			return startTime.until(finishTime, ChronoUnit.MINUTES);
		}
			
		return 0L;
	}

	@JsonIgnore
	public boolean isInvalid() {
		return invalid;
	}
	
	public void invalidate() {
		this.invalid = true;
	}
	
	public Long getOriginId() {
		return originId;
	}
	
	public Long getDestinyId() {
		return destinyId;
	}

	@Override
	public String toString() {
		return "Route [originId=" + originId + ", destinyId=" + destinyId + ", itineraries=" + itineraries
				+ ", invalid=" + invalid + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itineraries == null) ? 0 : itineraries.hashCode());
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
		Route other = (Route) obj;
		if (itineraries == null) {
			if (other.itineraries != null)
				return false;
		} else if (!itineraries.equals(other.itineraries))
			return false;
		return true;
	}

}
