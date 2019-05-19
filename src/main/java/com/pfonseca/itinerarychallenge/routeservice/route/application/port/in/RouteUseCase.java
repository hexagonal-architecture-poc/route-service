package com.pfonseca.itinerarychallenge.routeservice.route.application.port.in;

import com.pfonseca.itinerarychallenge.routeservice.route.application.domain.Route;
import com.pfonseca.itinerarychallenge.routeservice.route.application.filter.RouteFilter;
import com.pfonseca.itinerarychallenge.routeservice.route.application.service.strategy.SortStrategy;

public interface RouteUseCase {
    Route searchRoute(RouteFilter filter, SortStrategy strategy);
}
