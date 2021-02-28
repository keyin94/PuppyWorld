package com.example.ad.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ad.domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

}
