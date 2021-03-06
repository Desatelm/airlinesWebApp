package edu.mum.cs545.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;

import javax.ws.rs.core.MediaType;

import cs545.airline.model.Airline;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.AirportService;
import cs545.airline.service.FlightService;

@Named
@ApplicationScoped
public class RestFlightService implements Serializable {

	private String airlineQ;
	private String origin;
	private String destination;

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getAirlineQ() {
		return airlineQ;
	}

	public void setAirlineQ(String airlineQ) {
		this.airlineQ = airlineQ;
	}

	private static final long serialVersionUID = 1L;
	@Inject
	private FlightService flightService;
	@Inject
	private AirlineService airlineServic;
	@Inject
	private AirportService airportService;

	private List<Flight> flightList = new ArrayList<Flight>();

	public List<Flight> getFlightList() {
		return flightList;
	}

	public void setFlightList(ArrayList<Flight> flightList) {
		this.flightList = flightList;
	}

	
	public Flight update(Flight flight) {
		return flightService.update(flight);
	}

	
	public Flight find(Flight flight) {
		return flightService.find(flight);
	}

	
	public void findByNumber() {

		flightList = flightService.findByNumber(airlineQ);
		
	}

	
	public void findByAirline() {

		Airline airline = airlineServic.findByName(airlineQ);
		flightList = flightService.findByAirline(airline);
		
	}

	
	public String findByOrigin() {

		Airport airport = airportService.findByCode(origin);
		flightList = flightService.findByOrigin(airport);
		return "flightList";
	}

	
	public void findByDestination() {
		Airport airport = airportService.findByCode(destination);
		flightList = flightService.findByDestination(airport);
		
	}
	
	public List<Flight> findByDateArrival(Date datetime) {
		return flightService.findByArrival(datetime);
	}

	public String findAll() {
		flightList = flightService.findAll();

		return "flightList";
	}

}
