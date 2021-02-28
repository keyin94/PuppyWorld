package com.example.ad.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.ad.domain.Provider;
import com.example.ad.domain.Reservation;
import com.example.ad.domain.Services;
import com.example.ad.domain.Status;
import com.example.ad.domain.User;
import com.example.ad.service.EmailService;
import com.example.ad.service.ProviderService;
import com.example.ad.service.ProviderServiceImplementation;
import com.example.ad.service.ReservationService;
import com.example.ad.service.ReservationServiceImplementation;
import com.example.ad.service.ServiceService;
import com.example.ad.service.ServiceServiceImplementation;
import com.example.ad.service.UserService;
import com.example.ad.service.UserServiceImplementation;

@Controller
public class ServiceController {
	@Autowired
	private ServiceService sservice;
	
	@Autowired
	public void setSService(ServiceServiceImplementation sServiceImpl) {
		this.sservice = sServiceImpl;
	}
	
	@Autowired
	private ReservationService rservice;
	
	@Autowired
	public void setRService(ReservationServiceImplementation rServiceImpl) {
		this.rservice = rServiceImpl;
	}
	
	@Autowired
	private UserService uservice;
	
	@Autowired
	public void setUService(UserServiceImplementation uServiceImpl) {
		this.uservice = uServiceImpl;
	}
	
	@Autowired
	private ProviderService pvservice;
	
	@Autowired
	public void setPvService(ProviderServiceImplementation pvServiceImpl) {
		this.pvservice = pvServiceImpl;
	}
	
	@Autowired
	private EmailService eservice;
	

	@RequestMapping(value = "/serviceList/{id}")
	public String dateList(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {
		User user = uservice.findUserByUserName(request.getRemoteUser());
		ArrayList<Services> output = new ArrayList<Services>();
		ArrayList<Services> display = sservice.findAllActiveServices(id);
		for (Iterator <Services> iterator = display.iterator(); iterator.hasNext();) {
			Services services = iterator.next();
			if(!services.getTimeSlots().isEmpty()) {
				output.add(services);
			}
			
		}
		

		Provider provider = pvservice.findProviderById(id);
		model.addAttribute("serviceList", output);
		model.addAttribute("provider", provider);
		return "serviceList";
	}

	@RequestMapping("/serviceCreate/{id}")
	public String showNewServiceForm(@PathVariable("id") Integer id, Model model) {
		Services service = new Services();
		Provider provider = pvservice.findProviderById(id);
		service.setProvider(provider);
		model.addAttribute("service",service);
		return "serviceCreate";
	}
	
	@RequestMapping(value = "/reservationSave")
	public String saveReservation(@ModelAttribute("reservation")Reservation reservation, HttpServletRequest request) {
		Services service = sservice.findServiceById(reservation.getService().getServiceId());
		ArrayList<String> tSlot = service.getTimeSlots();
		String reserveTime = reservation.getTimeSlot();
		for (int i = 0; i<tSlot.size(); i++) {
			if(tSlot.get(i).equalsIgnoreCase(reserveTime)) {
				tSlot.remove(i);
			}
		}
		service.setTimeSlots(tSlot);
		sservice.saveService(service);
		User user = uservice.findUserByUserName(request.getRemoteUser());
		reservation.setUser(user);
		reservation.setStatus(Status.ACTIVE);
		String msg = "Thank you for choosing us. Your reservation with " + service.getProvider().getProviderName() + " on " + service.getLocalDate() + " at " + reserveTime + " has been successfully booked. See you soon.";
		try {
			eservice.sendSuccessNotification(msg, user.getEmailAddress());
		} catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		rservice.saveReservation(reservation);
		return "reservationSuccess";
	}
	
	@RequestMapping(value = "/reservationCreate/{id}")
	public String createReservation(@PathVariable("id") Integer id, Model model) {
		Services service = sservice.findServiceById(id);
		model.addAttribute("service", service);
		Reservation reservation = new Reservation();
		reservation.setService(service);
		System.out.println("step 1" + reservation.getService().getLocalDate());
		model.addAttribute("reservation", reservation);
		return "reservationCreate";
	}
	
	@RequestMapping(value = "/reservationDelete/{id}")
	public String deletePost(@PathVariable("id") Integer id) {
		Reservation reservation = rservice.findReservationById(id);
		Services service = reservation.getService();
		ArrayList<String> tSlot = service.getTimeSlots();
		String reserveTime = reservation.getTimeSlot();
		tSlot.add(reserveTime);
		service.setTimeSlots(tSlot);
		sservice.saveService(service);
		rservice.deleteReservationById(id);
		return "redirect:/profile";
	}

	
	@RequestMapping(value="/serviceSave",method=RequestMethod.POST)
	public String saveService(@ModelAttribute("service")@Valid Services service, Errors errors, BindingResult bindingResult){
		
		if (service.getLocalDate()==null) {
			errors.rejectValue("localDate","null", "must not be empty");
		}
		
		if (service.getTimeSlots().size()==0) {
			errors.rejectValue("timeSlots","empty", "must not be empty");
		}
		
		LocalDate date = LocalDate.now();
		if ( (service.getLocalDate() != null) && (service.getTimeSlots().size() != 0)) {
			if(service.getLocalDate().isBefore(date) || service.getLocalDate().isEqual(date)) {
				errors.rejectValue("localDate", "null", "Cannot be past or present");
			}
			
			ArrayList<Services> sList = sservice.findAllServices();
			for (Iterator <Services> iterator = sList.iterator(); iterator.hasNext();) {
				Services service1 = iterator.next();
				if(service1.getLocalDate().isEqual(service.getLocalDate()) & service1.getProvider().getProviderId() == service.getProvider().getProviderId()) {
					errors.rejectValue("localDate", "exist", "Timeslot exist");
					break;
				}
				
			}
		}
	
		if (bindingResult.hasErrors()) {
			return "serviceCreate";
		}
		
		service.setStatus(Status.ACTIVE);
		sservice.saveService(service);
		
		System.out.println("step 1" + service.getProvider().getProviderId());
		return "redirect:/serviceList/" + service.getProvider().getProviderId();
	}
	
	@RequestMapping(value = "/serviceDelete/{id}")
	public String deleteService(@PathVariable("id") Integer id, Model model) {
		Services service = sservice.findServiceById(id);
		
		
		ArrayList<Reservation> rList = rservice.findAllReservations();
		for (Iterator <Reservation> iterator = rList.iterator(); iterator.hasNext();) {
			Reservation reservation = iterator.next();
			if(reservation.getService().getServiceId() == service.getServiceId() & reservation.getStatus() == Status.ACTIVE) {
				String message = "Reservation exist";
				model.addAttribute("message", message);
				return "forward:/serviceList/" + service.getProvider().getProviderId();
			}
		}
		sservice.deleteServiceById(id);
		return "redirect:/serviceList/" + service.getProvider().getProviderId();
	}
	
	  @RequestMapping("/serviceBarGraph")
	    public String serviceBarGraph(Integer providerId, Model model) {
	        List localDate = new ArrayList();
	        List serviceSum = new ArrayList<Integer>();
	        List<Map<String, Object>> list = sservice.countLocalDate(providerId);
	        for (int i = 0; i < list.size(); i++) {
	            localDate.add(list.get(i).get("localDate"));
	            serviceSum.add(list.get(i).get("localDateSum"));
	        }
	        model.addAttribute("localDate", localDate);
	        model.addAttribute("serviceSum", serviceSum);
	        return "service_bar_graph";
	    }
	
}
