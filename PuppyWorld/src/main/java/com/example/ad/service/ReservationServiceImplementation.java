package com.example.ad.service;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ad.domain.Reservation;
import com.example.ad.domain.Status;
import com.example.ad.repo.ReservationRepository;

@Service
@Transactional
public class ReservationServiceImplementation implements ReservationService {
	
	@Autowired
	ReservationRepository rrepo;

	@Override
	public void saveReservation(Reservation reservation) {
		// TODO Auto-generated method stub
		rrepo.save(reservation);
	}

	@Override
	public ArrayList<Reservation> findAllReservations() {
		// TODO Auto-generated method stub
		return (ArrayList<Reservation>) rrepo.findAll();
	}

	@Override
	public Reservation findReservationById(int Id) {
		// TODO Auto-generated method stub
		ArrayList<Reservation> rList = findAllReservations();
		Reservation searchReservation = null;
		for (Iterator <Reservation> iterator = rList.iterator(); iterator.hasNext();) {
			Reservation reservation = iterator.next();
			if(reservation.getReservationId() == Id) {
				searchReservation = reservation;
			}
		}
		return searchReservation;
	}

	@Override
	public void deleteReservationById(int Id) {
		// TODO Auto-generated method stub
		Reservation searchReservation = findReservationById(Id);
		rrepo.delete(searchReservation);
	}

	@Override
	public ArrayList<Reservation> findAllReservationsByUserId(int userId) {
		ArrayList<Reservation> rList = findAllReservations(); 
		ArrayList<Reservation> outputList = new ArrayList<Reservation>();
		for (Iterator <Reservation> iterator = rList.iterator(); iterator.hasNext();) {
			Reservation reservation = iterator.next();
			if(reservation.getUser().getUserId() == userId) {
				outputList.add(reservation);
			}
		}
		// TODO Auto-generated method stub
		return outputList;
	}

	@Override
	public ArrayList<Reservation> findAllActiveReservation(int userId) {
		ArrayList<Reservation> display = new ArrayList<Reservation>();
		ArrayList<Reservation> rList = findAllReservationsByUserId(userId);
		for (Iterator <Reservation> iterator = rList.iterator(); iterator.hasNext();) {
			Reservation reservation = iterator.next();
			if(reservation.getStatus() == Status.ACTIVE) {
				display.add(reservation);
			}
			
		}
		return display;
	}
	
}
