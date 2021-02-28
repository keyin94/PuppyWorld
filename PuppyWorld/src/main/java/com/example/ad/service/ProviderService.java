package com.example.ad.service;

import java.util.ArrayList;

import com.example.ad.domain.Provider;

public interface ProviderService {
	
	public void saveProvider(Provider provider);
	public ArrayList<Provider> findAllProviders();
	public Provider findProviderById(int Id);
	public void deleteProviderById(int Id);
	
	public ArrayList<Provider> searchProviderByKeyword(String keyword);

}
