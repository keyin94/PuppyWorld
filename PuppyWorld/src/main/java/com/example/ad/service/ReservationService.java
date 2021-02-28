package com.example.ad.service;

import java.util.ArrayList;

import com.example.ad.domain.Reservation;

public interface ReservationService {
	
	public void saveReservation(Reservation reservation);
	public ArrayList<Reservation> findAllReservations();
	public Reservation findReservationById(int Id);
	public void deleteReservationById(int Id);
	public ArrayList<Reservation> findAllReservationsByUserId(int userId);
	public ArrayList<Reservation> findAllActiveReservation(int userId);
	

}
