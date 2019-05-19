package com.pfonseca.itinerarychallenge.routeservice.route.application.port.in;

import com.pfonseca.itinerarychallenge.routeservice.route.domain.Route;
import com.pfonseca.itinerarychallenge.routeservice.route.application.filter.RouteFilter;
import com.pfonseca.itinerarychallenge.routeservice.route.application.service.strategy.SortStrategy;

import java.util.Optional;

public interface RouteUseCase {
    Optional<Route> searchRoute(RouteFilter filter, SortStrategy strategy);
}
