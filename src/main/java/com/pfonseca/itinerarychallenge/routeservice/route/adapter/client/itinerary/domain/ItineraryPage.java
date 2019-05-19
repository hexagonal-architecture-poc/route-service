package com.pfonseca.itinerarychallenge.routeservice.route.adapter.client.itinerary.domain;

import java.util.List;

public class ItineraryPage {

	private List<Itinerary> content;

	public List<Itinerary> getContent() {
		return content;
	}

	public void setContent(List<Itinerary> content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ItineraryPage [content=" + content + "]";
	}
	
}
