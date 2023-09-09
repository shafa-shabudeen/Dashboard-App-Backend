package com.example.dashboard.repository;

import com.example.dashboard.model.dashboard;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface dashboardRepository extends CrudRepository<dashboard, Long> {
    
     Optional<dashboard> findById(Long id);

     Iterable<dashboard> findBySector(String sector);

     Iterable<dashboard> findByPestle(String pestle);

     Iterable<dashboard> findBySwot(String swot);

     Iterable<dashboard> findByTopic(String topic);

     Iterable<dashboard> findByCity(String city);

     Iterable<dashboard> findByRegion(String region);
     
     Iterable<dashboard> findByRelevance(String relevance);

     Iterable<dashboard> findByCountry(String country);

     Iterable<dashboard> findBySectorAndTitle(String sector,String title);

     Iterable<dashboard> findByTopicAndTitle(String topic,String title);


}
