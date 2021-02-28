package com.example.ad.service;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ad.domain.Provider;
import com.example.ad.repo.ProviderRepository;

@Service
@Transactional
public class ProviderServiceImplementation implements ProviderService {
	
	@Autowired
	ProviderRepository pvrepo;

	@Override
	public void saveProvider(Provider provider) {
		// TODO Auto-generated method stub
		pvrepo.save(provider);
	}

	@Override
	public ArrayList<Provider> findAllProviders() {
		// TODO Auto-generated method stub
		return (ArrayList<Provider>) pvrepo.findAll();
	}

	@Override
	public Provider findProviderById(int Id) {
		// TODO Auto-generated method stub
		ArrayList<Provider> pList = findAllProviders();
		Provider searchProvider = null;
		for (Iterator <Provider> iterator = pList.iterator(); iterator.hasNext();) {
			Provider provider = iterator.next();
			if(provider.getProviderId() == Id) {
				searchProvider = provider;
			}
			
		}
		return searchProvider;
	}

	@Override
	public void deleteProviderById(int Id) {
		// TODO Auto-generated method stub
		Provider searchProvider = findProviderById(Id);
		pvrepo.delete(searchProvider);
	}

	@Override
	public ArrayList<Provider> searchProviderByKeyword(String keyword) {
		// TODO Auto-generated method stub
		if(keyword!=null) {
			return pvrepo.searchProvider(keyword);
		}
		return (ArrayList<Provider>) pvrepo.findAll();
	}

}
