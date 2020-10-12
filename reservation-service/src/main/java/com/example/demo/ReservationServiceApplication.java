package com.example.demo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import model.repository.Reservation;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ReservationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationServiceApplication.class, args);
	}
	
	@Bean
	RouterFunction<ServerResponse> routes(ReservationHandler handler){
		return RouterFunctions.route(RequestPredicates.GET("/reservations/{id}"), handler::byId);
		
	}
}
@RestController
class ReservationRestController{
	
	private final ReservationRepository repository;

	public ReservationRestController(ReservationRepository repository) {
				this.repository = repository;
	}
	
	@GetMapping("/reservations")
	Flux<Reservation> all(){
		return this.repository.findAll();
		
	}
	
}

@Component 
class Initializer implements ApplicationRunner{

	private final ReservationRepository repo;
	
	public Initializer(ReservationRepository repo) {
		
		this.repo = repo;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Flux.just("A","B","C").map(name->new Reservation(null,name))
		.flatMap(r->this.repo.save(r))
		.subscribe(r->System.out.println("reservations:" +r.toString()));
		
		
	}
	
	
}


interface ReservationRepository extends ReactiveMongoRepository<Reservation,String> {
	
Flux<Reservation> findByReservationName(String rn);	
}


