package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import model.repository.Reservation;
import reactor.core.publisher.Mono;

@Component
class ReservationHandler {

	private final ReservationRepository repository;

	ReservationHandler(ReservationRepository repository) {
			this.repository = repository;
	}

	public Mono<ServerResponse> byId(ServerRequest request) {
		String id = request.pathVariable("id");
		Mono<Reservation> byIdResults = this.repository.findById(id);
		return ServerResponse.ok().body(byIdResults, Reservation.class);
	}

}