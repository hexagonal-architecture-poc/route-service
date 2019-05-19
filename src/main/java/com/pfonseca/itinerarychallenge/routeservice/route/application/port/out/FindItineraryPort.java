package com.pfonseca.itinerarychallenge.routeservice.route.application.port.out;

import com.pfonseca.itinerarychallenge.routeservice.route.adapter.client.itinerary.domain.Itinerary;

import java.time.LocalTime;
import java.util.List;

public interface FindItineraryPort {
    List<Itinerary> getItinerariesFromOrigin(Long origin, LocalTime min);
}
