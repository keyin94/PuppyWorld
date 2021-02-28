package com.example.ad.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int reservationId;
	
	@OneToOne
	private Services service;
	
	private String timeSlot;
	
	@OneToOne
	private User user;
	
	private Status status;

	public Reservation() {
		super();
	}

	public Reservation(Services service, String timeSlot, User user, Status status) {
		super();
		this.service = service;
		this.timeSlot = timeSlot;
		this.user = user;
		this.status = status;
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public Services getService() {
		return service;
	}

	public void setService(Services service) {
		this.service = service;
	}

	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public static Comparator<Reservation> ReserveComparator = new Comparator<Reservation>() {
		   
        public int compare(Reservation o1, Reservation o2) {
            // TODO Auto-generated method stub
           
            if (o1.getService().getLocalDate().isEqual(o2.getService().getLocalDate()))
                return 0;
            else if (o1.getService().getLocalDate().isBefore(o2.getService().getLocalDate()))
                return 1;
            else
                return -1;
        }
   
    };
    
    public static Comparator<Reservation> ReverseComparator = new Comparator<Reservation>() {
		   
        public int compare(Reservation o1, Reservation o2) {
            // TODO Auto-generated method stub
           
            if (o1.getService().getLocalDate().isEqual(o2.getService().getLocalDate()))
                return 0;
            else if (o1.getService().getLocalDate().isAfter(o2.getService().getLocalDate()))
                return 1;
            else
                return -1;
        }
   
    };
	

}
