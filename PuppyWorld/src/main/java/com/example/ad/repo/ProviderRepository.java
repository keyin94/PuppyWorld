package com.example.ad.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ad.domain.Provider;

public interface ProviderRepository extends JpaRepository<Provider, Integer> {
	
	@Query(value = "SELECT * FROM Provider p "
			+"WHERE p.provider_description LIKE %:keyword% "
			+"OR p.provider_name LIKE %:keyword% "
			+"OR p.price LIKE %:keyword% ",
			nativeQuery=true)
	public ArrayList<Provider> searchProvider(@Param("keyword") String keyword);

}
