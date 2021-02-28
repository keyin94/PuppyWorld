package com.example.ad.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.ad.domain.Provider;
import com.example.ad.domain.Reservation;
import com.example.ad.domain.Status;
import com.example.ad.domain.User;
import com.example.ad.service.ProviderService;
import com.example.ad.service.ProviderServiceImplementation;
import com.example.ad.service.ReservationService;
import com.example.ad.service.ReservationServiceImplementation;
import com.example.ad.service.UserService;
import com.example.ad.service.UserServiceImplementation;

@Controller
public class ProviderController {
	
	@Autowired
	private ProviderService pvservice;
	
	@Autowired
	public void setPvService(ProviderServiceImplementation pvServiceImpl) {
		this.pvservice = pvServiceImpl;
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
	
	@RequestMapping(value="/searchProvider")
	public String searchProvider(Model model, @RequestParam("keyword") String keyword) {
		
		ArrayList<Provider> searchProvider=pvservice.searchProviderByKeyword(keyword);
		Collections.reverse(searchProvider);
		model.addAttribute("providerList",searchProvider);
		model.addAttribute("keyword",keyword);
		return "providerList";
	}
	
	
	
	@RequestMapping(value="/providerList") // ???
	public String list(Model model, HttpServletRequest request)
	{
		ArrayList<Provider> pList = pvservice.findAllProviders();
		int length = pList.size();
		
		Collections.reverse(pList);
		model.addAttribute("providerList", pList);
		model.addAttribute("length", length);
		int productId= 0;
		
		model.addAttribute("productId", productId);
		
		User user = uservice.findUserByUserName(request.getRemoteUser());
		model.addAttribute("user", user);
		
		String keyword="";
		model.addAttribute("keyword",keyword);
		
		return "providerList";
	}
	
	@GetMapping("/provider/image/{id}")
	public void showProviderImage(@PathVariable String id, HttpServletResponse response) throws IOException {
		response.setContentType("image/jpeg"); 
		
		int newId = Integer.parseInt(id);
		Provider provider = pvservice.findProviderById(newId);

		InputStream is = new ByteArrayInputStream(provider.getProviderImage());
		IOUtils.copy(is, response.getOutputStream());
	}
	
	@RequestMapping("/providerCreate")
	public String showNewProductForm(Model model) {
		Provider provider = new Provider();
		model.addAttribute("provider",provider);
		return "providerCreate";
	}
	
	@RequestMapping(value="/providerSave",method=RequestMethod.POST)
	public String saveService(@ModelAttribute("provider")@Valid Provider provider, Errors errors, BindingResult bindingResult, @RequestParam("fileImage") MultipartFile multipartFile, HttpServletRequest request) throws IllegalStateException, IOException {
		
		try {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			System.out.println(fileName);
			File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
			multipartFile.transferTo(convFile);
			byte[] fileContent = FileUtils.readFileToByteArray(convFile);
			
			provider.setProviderImage(fileContent);
		}
		catch(IllegalStateException e) {
			System.out.println(e.toString());
		}
		catch(IOException e) {
			System.out.println(e.toString());
		}
		
		ArrayList<Provider> pList = pvservice.findAllProviders();
		for (Iterator <Provider>iterator = pList.iterator(); iterator.hasNext();) {
			Provider provider2 =  iterator.next();
			if(provider2.getProviderName().equalsIgnoreCase(provider.getProviderName())) {
				errors.rejectValue("providerName", "exist", "Service Exist");
				break;
			}
			
		}
		
		if (provider.getPrice() == 0) {
			errors.rejectValue("price","not zero", "must not be zero");
		}
		
		if (bindingResult.hasErrors()) {
			return "providerCreate";
		}
		pvservice.saveProvider(provider);
		
		return "redirect:/providerList";
	}
	
	@RequestMapping(value = "/providerEdit/{id}")
	public String editProduct(@PathVariable("id") Integer id, Model model) {
		Provider provider = pvservice.findProviderById(id);
		String encodedString = Base64.getEncoder().encodeToString(provider.getProviderImage());
		model.addAttribute("image", encodedString);
		model.addAttribute("provider",provider);
		return "providerEdit";
	}
	
	@RequestMapping(value = "/providerDelete/{id}")
	public String deleteProduct(@PathVariable("id") Integer id, Model model) {
		
		Provider provider = pvservice.findProviderById(id);
		ArrayList<Reservation> rList = rservice.findAllReservations();
		for (Iterator <Reservation> iterator = rList.iterator(); iterator.hasNext();) {
			Reservation reservation = iterator.next();
			if(reservation.getService().getProvider().getProviderId() == provider.getProviderId()) {
				String message = "Reservation exist";
				model.addAttribute("message", message);
				return "forward:/providerList";
			}
		}
		pvservice.deleteProviderById(id);
		
		return "redirect:/providerList";
	}
	
	@RequestMapping(value="/providerEditSave",method=RequestMethod.POST)
	public String editSaveProvider(@ModelAttribute("provider")@Valid Provider provider, Errors errors, BindingResult bindingResult, @RequestParam("fileImage") MultipartFile multipartFile, HttpServletRequest request) throws IllegalStateException, IOException {
		
		try {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			System.out.println(fileName);
			File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
			multipartFile.transferTo(convFile);
			byte[] fileContent = FileUtils.readFileToByteArray(convFile);
			
			provider.setProviderImage(fileContent);
		}
		catch(IllegalStateException e) {
			System.out.println(e.toString());
		}
		catch(IOException e) {
			System.out.println(e.toString());
		}
		
		if(provider.getProviderImage() == null) {
			Provider oldProvider = pvservice.findProviderById(provider.getProviderId());
			provider.setProviderImage(oldProvider.getProviderImage());	
		}
		
		if(provider.getPrice() == 0) {
			errors.rejectValue("price", "null", "Must be filled");
		}
	
		if (bindingResult.hasErrors()) {
			return "providerEdit";
		}

		pvservice.saveProvider(provider);
		
		return "redirect:/providerList";
	}
	
	@RequestMapping(value = "/providerReservation/{id}")
	public String providerReservation(@PathVariable("id") Integer id, Model model) {
		
		Provider provider = pvservice.findProviderById(id);
		ArrayList<Reservation> rList = rservice.findAllReservations();
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		for (Iterator <Reservation> iterator = rList.iterator(); iterator.hasNext();) {
			Reservation reservation = iterator.next();
			if(reservation.getService().getProvider().getProviderId() == provider.getProviderId() & reservation.getStatus() == Status.ACTIVE) {
				reservations.add(reservation);
			}
		}
		model.addAttribute("reservations",reservations);
		model.addAttribute("provider", provider);
		return "reservationBooked";
	}
	
	@RequestMapping(value="/reservationList") // ???
	public String reservationList(Model model, HttpServletRequest request)
	{
		ArrayList<Reservation> rList = rservice.findAllReservations();
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		for (Iterator <Reservation> iterator = rList.iterator(); iterator.hasNext();) {
			Reservation reservation = iterator.next();
			if(reservation.getStatus() == Status.ACTIVE) {
				reservations.add(reservation);
			}
		}
		Collections.sort(reservations, Reservation.ReverseComparator);
		model.addAttribute("reservations",reservations);
		
		return "reservationList";
	}

}
