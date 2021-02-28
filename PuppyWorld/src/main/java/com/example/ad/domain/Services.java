package com.example.ad.domain;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

@Entity
public class Services {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int serviceId;
	
	private ArrayList<String> timeSlots;
	
	@OneToOne
	private Provider provider;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate localDate;
	
	private Status status;

	public Services(ArrayList<String> timeSlots, Provider provider, LocalDate localDate) {
		super();
		status = Status.ACTIVE;
		this.timeSlots = timeSlots;
		this.provider = provider;
		this.localDate = localDate;
	}

	public Services() {
		super();
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public ArrayList<String> getTimeSlots() {
		return timeSlots;
	}

	public void setTimeSlots(ArrayList<String> timeSlots) {
		this.timeSlots = timeSlots;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public LocalDate getLocalDate() {
		return localDate;
	}

	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}

	@Override
	public String toString() {
		return "Services [serviceId=" + serviceId + ", timeSlots=" + timeSlots + ", provider=" + provider
				+ ", localDate=" + localDate + "]";
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	

}
