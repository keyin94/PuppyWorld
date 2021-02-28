package com.example.ad.repo;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ad.domain.Services;

public interface ServiceRepository extends JpaRepository<Services, Integer> {
	
	

	@Query(value = "SELECT * FROM Services s " 
	   +"WHERE s.service_name LIKE %:keyword% " 
	   +"OR s.service_description LIKE %:keyword% " 
	   +"OR s.service_duration LIKE %:keyword% " 
	   +"OR CONCAT(s.charges, '') LIKE %:keyword% " 
	   +"OR s.reservation_type LIKE %:keyword% " 
	   + "OR s.limit_dogs LIKE %:keyword% ",
	   nativeQuery = true)
	public ArrayList<Services> searchService(@Param("keyword") String keyword);

	@Query(value = "select DATE_FORMAT(s.local_date,'%Y-%m-%d') localDate,count(s.local_date) localDateSum from services s, reservation r where s.service_id=r.service_service_id and s.provider_provider_id=:providerId group by s.local_date order by s.local_date", nativeQuery = true)
    List<Map<String, Object>> countLocalDate(@Param("providerId")int providerId);
	

}
