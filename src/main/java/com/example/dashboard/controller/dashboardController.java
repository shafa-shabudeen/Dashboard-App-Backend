package com.example.dashboard.controller;

import com.example.dashboard.model.dashboard;
import com.example.dashboard.service.dashboardservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/dashboard")
@CrossOrigin
public class dashboardController {

    @Autowired
    public dashboardservice dbsr;

    @GetMapping("/allData")
    public ResponseEntity<Iterable<dashboard>> getAllData(){
        return new  ResponseEntity<Iterable<dashboard>>(dbsr.allData(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Optional<dashboard>> getById(@PathVariable Long id){
        return new ResponseEntity<Optional<dashboard>>(dbsr.getById(id),HttpStatus.OK);
    }

    @GetMapping("/allSector")
    public ResponseEntity<List<String>> getAllSector(){
        return new ResponseEntity<List<String>>(dbsr.allSector(), HttpStatus.OK);
    }

    @GetMapping("/allRegion")
    public ResponseEntity<List<String>> getAllRegion(){
        return new ResponseEntity<List<String>>(dbsr.allRegion(), HttpStatus.OK);
    }

    @GetMapping("/title/{sector}")
    public ResponseEntity<List<String>> getTitleBySector(@PathVariable String sector){
        return new ResponseEntity<List<String>>(dbsr.getTitleBySector(sector), HttpStatus.OK);
    }

    @GetMapping("/sectorAndTitle")
    public ResponseEntity<Map<String,Integer>> getSectorTitle(){
        return new ResponseEntity<Map<String,Integer>>(dbsr.getTitleAndSector(),HttpStatus.OK);
    }

    @GetMapping("/pestleAndTopic")
    public ResponseEntity<Map<String,Integer>> getPestleTopic(){
        return new ResponseEntity<Map<String,Integer>>(dbsr.getPestleAndCount(),HttpStatus.OK);
    }

    @GetMapping("/swotAndTopic")
    public ResponseEntity<Map<String,Integer>> getSwotTopic(){
        return new ResponseEntity<Map<String,Integer>>(dbsr.getSwotAndCount(),HttpStatus.OK);
    }

    @GetMapping("/topic")
    public ResponseEntity<List<String>> getPublished(){
        return new ResponseEntity<List<String>>(dbsr.allTopic(), HttpStatus.OK);
    }

    @GetMapping("/impact/{topic}")
    public ResponseEntity<List<String>> getImpactByTopic(@PathVariable String topic){
        return new ResponseEntity<List<String>>(dbsr.getImpactByTopic(topic), HttpStatus.OK);
    }

    @GetMapping("/likelihood/{topic}")
    public ResponseEntity<List<String>> getLikelihoodByTopic(@PathVariable String topic){
        return new ResponseEntity<List<String>>(dbsr.getLikelihoodByTopic(topic), HttpStatus.OK);
    }

    @GetMapping("/likelihoodCount")
    public ResponseEntity<Map<String,Integer>> getLikelihoodCount(){
        return new ResponseEntity<Map<String,Integer>>(dbsr.getLikelihoodCount(), HttpStatus.OK);
    }

    @GetMapping("/impactCount")
    public ResponseEntity<Map<String,Integer>> getImpactCount(){
        return new ResponseEntity<Map<String,Integer>>(dbsr.getimpactCount(), HttpStatus.OK);
    }

    @GetMapping("/topSectors")
    public ResponseEntity<Set<String>> getTopicSectors(){
        return new ResponseEntity<Set<String>>(dbsr.getTopSector(), HttpStatus.OK);
    }

    @GetMapping("/country/{sector}")
    public ResponseEntity<List<String>> getCountryBySector(@PathVariable String sector){
        return new ResponseEntity<List<String>>(dbsr.getCountryBySector(sector), HttpStatus.OK);
    }

    @GetMapping("/countryAndSector")
    public ResponseEntity<Map<String,Integer>> getCountrySectorCount(){
        return new ResponseEntity<Map<String,Integer>>(dbsr.getSectorAndCountryCount(),HttpStatus.OK);
    }

    @GetMapping("/publishedAndSector")
    public ResponseEntity<Map<String,Integer>> getPublishedSectorCount(){
        return new ResponseEntity<Map<String,Integer>>(dbsr.getSectorAndPublishedCount(),HttpStatus.OK);
    }

    @GetMapping("/swotAndSector")
    public ResponseEntity<Map<String,Integer>> getSwotSectorCount(){
        return new ResponseEntity<Map<String,Integer>>(dbsr.getSectorAndSwotCount(),HttpStatus.OK);
    }

    @GetMapping("/startYearCount")
    public ResponseEntity<Map<String,Integer>> getStartYearCount(){
        return new ResponseEntity<Map<String,Integer>>(dbsr.getStartYearCount(), HttpStatus.OK);
    }

    @GetMapping("/endYearCount")
    public ResponseEntity<Map<String,Integer>> getEndYearCount(){
        return new ResponseEntity<Map<String,Integer>>(dbsr.getEndYearCount(), HttpStatus.OK);
    }

    @GetMapping("/addedYearCount")
    public ResponseEntity<Map<String,Integer>> getAddedYearCount(){
        return new ResponseEntity<Map<String,Integer>>(dbsr.getAddedYearCount(), HttpStatus.OK);
    }

    @GetMapping("/relevanceCityCount")
    public ResponseEntity<Map<String,Integer>> getRelevanceCityCount(){
        return new ResponseEntity<Map<String,Integer>>(dbsr.getCityAndRelevanceCount(), HttpStatus.OK);
    }

    @GetMapping("/intensityCityCount")
    public ResponseEntity<Map<String,Integer>> getIntensityCityCount(){
        return new ResponseEntity<Map<String,Integer>>(dbsr.getCityAndIntensityCount(), HttpStatus.OK);
    }

    @GetMapping("/regionAndTitleCount")
    public ResponseEntity<Map<String,Integer>> getRegionTitleCount(){
        return new ResponseEntity<Map<String,Integer>>(dbsr.getTitleAndRegion(), HttpStatus.OK);
    }

    @GetMapping("/regionAndSectorCount")
    public ResponseEntity<Map<String,Integer>> getRegionSectorCount(){
        return new ResponseEntity<Map<String,Integer>>(dbsr.getSectorAndRegion(), HttpStatus.OK);
    }

    @GetMapping("/pestleRelevanceCount")
    public ResponseEntity<Map<String,Integer>> getPestleRelevanceCount(){
        return new ResponseEntity<Map<String,Integer>>(dbsr.getPestleAndRelevance(), HttpStatus.OK);
    }

    @GetMapping("/swotRelevanceCount")
    public ResponseEntity<Map<String,Integer>> getSwotRelevanceCount(){
        return new ResponseEntity<Map<String,Integer>>(dbsr.getSwotAndRelevance(), HttpStatus.OK);
    }

    @GetMapping("/sectorRelevanceCount")
    public ResponseEntity<Map<String,Integer>> getSectorRelevanceCount(){
        return new ResponseEntity<Map<String,Integer>>(dbsr.getSectorAndRelevanceCount(), HttpStatus.OK);
    }

    @GetMapping("/sectorIntensityCount")
    public ResponseEntity<Map<String,Integer>> getSectorIntensityCount(){
        return new ResponseEntity<Map<String,Integer>>(dbsr.getSectorAndIntensityCount(), HttpStatus.OK);
    }

    @GetMapping("titleAndUrl/{sector}")
    public ResponseEntity<Map<String,String>> getTitleAndUrlBySector(@PathVariable String sector){
        return new ResponseEntity<Map<String,String>>(dbsr.getTitleAndUrlBySector(sector), HttpStatus.OK);
    }

    @GetMapping("titleAndSource/{topic}")
    public ResponseEntity<Map<String,String>> getTitleAndSourceByTopic(@PathVariable String topic){
        return new ResponseEntity<Map<String,String>>(dbsr.getTitleAndSourceByTopic(topic), HttpStatus.OK);
    }

    @GetMapping("titleAndInsight/{topic}")
    public ResponseEntity<Map<String,String>> getTitleAndInsightByTopic(@PathVariable String topic){
        return new ResponseEntity<Map<String,String>>(dbsr.getTitleAndInsightByTopic(topic), HttpStatus.OK);
    }

    @GetMapping("/countryPestleCount")
    public ResponseEntity<Map<String,Integer>> getCountryPestleCount(){
        return new ResponseEntity<Map<String,Integer>>(dbsr.getPestleAndCountry(), HttpStatus.OK);
    }

    @GetMapping("/countrySwotCount")
    public ResponseEntity<Map<String,Integer>> getCountrySwotCount(){
        return new ResponseEntity<Map<String,Integer>>(dbsr.getSwotAndCountry(), HttpStatus.OK);
    }

    @GetMapping("/country")
    public ResponseEntity<List<String>> getCountry(){
        return new ResponseEntity<List<String>>(dbsr.allCountry(),HttpStatus.OK);
    }

    @GetMapping("/allData/{country}")
    public  ResponseEntity<Iterable<dashboard>> getByCountry(@PathVariable String country){
        return new ResponseEntity<Iterable<dashboard>>(dbsr.getByCountry(country),HttpStatus.OK);
    }
}