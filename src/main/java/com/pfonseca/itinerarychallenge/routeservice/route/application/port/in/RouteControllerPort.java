package com.pfonseca.itinerarychallenge.routeservice.route.application.port.in;

import com.pfonseca.itinerarychallenge.routeservice.route.application.domain.Route;
import com.pfonseca.itinerarychallenge.routeservice.route.application.filter.RouteFilter;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;

public interface RouteControllerPort {

    @GetMapping("/less-time")
    @ApiOperation(value="Find a route based in the less time")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "origin", value = "Origin city id", required = true ),
            @ApiImplicitParam(name = "destiny", value = "Destiny city id", required = true)
    })
    Route lessTime(@Valid RouteFilter filter);

    @GetMapping("/less-connections")
    @ApiOperation(value="Find a route based in the less number of connections")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "origin", value = "Origin city id", required = true),
            @ApiImplicitParam(name = "destiny", value = "Destiny city id", required = true)
    })
    Route lessConnections(@Valid RouteFilter filter);

}