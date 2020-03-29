package model.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Reservation {

	@Id
	private String id;
	private String reservationName;
	
	
	
	public Reservation(String id, String reservationName) {
		super();
		this.id = id;
		this.reservationName = reservationName;
	}
	
	
	

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", reservationName=" + reservationName + "]";
	}




	public Reservation() {
		super();
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReservationName() {
		return reservationName;
	}
	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
	
	
}
