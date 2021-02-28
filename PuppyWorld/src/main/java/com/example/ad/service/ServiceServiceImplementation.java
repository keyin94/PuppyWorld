package com.example.ad.service;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ad.domain.Provider;
import com.example.ad.domain.Services;
import com.example.ad.domain.Status;
import com.example.ad.repo.ProviderRepository;
import com.example.ad.repo.ServiceRepository;




@Service
@Transactional
public class ServiceServiceImplementation implements ServiceService {
    
    @Autowired
    private ServiceRepository srepo;
 
    @Override
    public void saveService(Services service) {
        // TODO Auto-generated method stub
        srepo.save(service);
        
    }
    
    @Override
    public ArrayList<Services> findAllServices() {
        // TODO Auto-generated method stub
        return (ArrayList<Services>) srepo.findAll();
    }

    @Override
    public Services findServiceById(int Id) {
        // TODO Auto-generated method stub
        ArrayList<Services> sList = findAllServices();
        Services searchService = null;
        for (Iterator <Services>iterator = sList.iterator(); iterator.hasNext();) {
            Services service = iterator.next();
            if(service.getServiceId() == Id) {
                searchService = service;
            }
        }
        return searchService;
    }

    @Override
    public void deleteServiceById(int Id) {
        // TODO Auto-generated method stub
        Services searchService = findServiceById(Id);
        srepo.delete(searchService);
    }


	@Override
	public ArrayList<Services> findAllServicesByProviderId(int Id) {
		ArrayList<Services> sList = findAllServices();
		ArrayList<Services> newList = new ArrayList<Services>();
		for (Iterator <Services>iterator = sList.iterator(); iterator.hasNext();) {
			Services services = iterator.next();
			System.out.println("step 2 " + Id);
			System.out.println("step 2.1 " + services.getProvider().getProviderId());
			if(services.getProvider().getProviderId() == Id) {
				System.out.println("step 2.2");
				newList.add(services);
				System.out.println("step 2.3");
			}
		}
		System.out.println("step 3 ");
		return newList;
	}



	@Override
	public ArrayList<Services> searchServiceByKeyword(String keyword) {
		if(keyword!=null) {
			return srepo.searchService(keyword);
		}
		return (ArrayList<Services>) srepo.findAll();
	}

	@Override
	public ArrayList<Services> findAllActiveServices(int id) {
		ArrayList <Services> display = new ArrayList <Services> ();
		ArrayList <Services> sList = findAllServicesByProviderId(id);
		for (Iterator <Services> iterator = sList.iterator(); iterator.hasNext();) {
			Services services = iterator.next();
			if(services.getStatus() == Status.ACTIVE) {
				display.add(services);
			}
			
		}return display;
	}
	
	@Override
    public List<Map<String, Object>> countLocalDate(int providerId) {
        return srepo.countLocalDate(providerId);
    }






}