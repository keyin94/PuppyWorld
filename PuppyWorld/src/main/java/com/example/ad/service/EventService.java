package com.example.ad.service;

import java.util.ArrayList;

import com.example.ad.domain.Event;

public interface EventService {
	
	public void saveEvent(Event event);
	public ArrayList<Event> findAllEvents();
	public Event findEventById(int Id);
	public void deleteEventById(int Id);

}
