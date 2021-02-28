package com.example.ad.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ad.domain.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {

}
