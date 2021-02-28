package com.example.ad.service;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.ad.domain.Event;
import com.example.ad.repo.EventRepository;

public class EventServiceImplementation implements EventService {
	
	@Autowired
	EventRepository erepo;

	@Override
	public void saveEvent(Event event) {
		// TODO Auto-generated method stub
		erepo.save(event);
	}

	@Override
	public ArrayList<Event> findAllEvents() {
		// TODO Auto-generated method stub
		return (ArrayList<Event>) erepo.findAll();
		
	}

	@Override
	public Event findEventById(int Id) {
		// TODO Auto-generated method stub
		ArrayList<Event> eList = findAllEvents();
		Event searchEvent = null;
		for (Iterator <Event>iterator = eList.iterator(); iterator.hasNext();) {
			Event event = iterator.next();
			if(event.getEventId() == Id) {
				searchEvent = event;
			}
		}
		return searchEvent;
	}

	@Override
	public void deleteEventById(int Id) {
		// TODO Auto-generated method stub
		Event searchEvent = findEventById(Id);
		erepo.delete(searchEvent);
	}

}
