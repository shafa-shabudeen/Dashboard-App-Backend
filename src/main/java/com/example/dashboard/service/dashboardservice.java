package com.example.dashboard.service;

import com.example.dashboard.model.dashboard;
import com.example.dashboard.repository.dashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class dashboardservice {
    @Autowired
    private dashboardRepository dbrp;
    // Retriving all data
    public Iterable<dashboard> allData(){
        return dbrp.findAll();
    }
    // Get by Id
    public Optional<dashboard> getById(Long id){
        return dbrp.findById(id);
    }
    //BASED UPON SECTORS
    //Get all sectors
    public List<String> allSector(){
        Iterable<dashboard> dashboardDatas = dbrp.findAll();
        Set<String> sectorSet = new HashSet<>();

        for(dashboard dashboardData: dashboardDatas){
            sectorSet.add(dashboardData.getSector());
        }

        return sectorSet.stream().collect(Collectors.toList());
    }
    //get title for selected sector
    public List<String> getTitleBySector(String sector){
        Iterable<dashboard> dashboardDatas = dbrp.findBySector(sector);
        Set<String> titleSet = new HashSet<>();

        for(dashboard dashboardData: dashboardDatas){
            titleSet.add(dashboardData.getTitle());
        }

        return titleSet.stream().collect(Collectors.toList());
    }
    // get country for selected sector
    public List<String> getCountryBySector(String sector){
        Iterable<dashboard> dashboardDatas = dbrp.findBySector(sector);
        Set<String> CountrySet = new HashSet<>();

        for(dashboard dashboardData: dashboardDatas){
            CountrySet.add(dashboardData.getCountry());
        }

        return CountrySet.stream().collect(Collectors.toList());
    }
    // get swot for selected sector
    public List<String> getSwotBySector(String sector){
        Iterable<dashboard> dashboardDatas = dbrp.findBySector(sector);
        Set<String> swotSet = new HashSet<>();

        for(dashboard dashboardData: dashboardDatas){
            swotSet.add(dashboardData.getSwot());
        }

        return swotSet.stream().collect(Collectors.toList());
    }
    //get published for selected sector
    public List<String> getPublishedBySector(String sector){
        Iterable<dashboard> dashboardDatas = dbrp.findBySector(sector);
        Set<String> publishedSet = new HashSet<>();

        for(dashboard dashboardData: dashboardDatas){
            publishedSet.add(dashboardData.getPublished());
        }

        return publishedSet.stream().collect(Collectors.toList());
    }
    // get top 5 sectors based on count of title
    public Set<String> getTopSector(){
        Set<String> topSectorSet = new HashSet<>();
        Map<String,Integer> sectorAndTitle = new HashMap<>();
        sectorAndTitle = getTitleAndSector();
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(sectorAndTitle.entrySet());

        Collections.sort(entryList, (e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        Set<String> topSectors = sortedMap.keySet();
        int count =0;
        for (String value : topSectors ){
            topSectorSet.add(value);
            count++;
            if (count == 5) {
                break;
            }
        }

        return topSectorSet;
    }
    //relevance for selected sector
    public List<String> getRelevanceBySector(String sector){
        Iterable<dashboard> dashboardDatas = dbrp.findBySector(sector);
        List<String> relevanceSet = new ArrayList<>();

        for(dashboard dashboardData: dashboardDatas){
            if(dashboardData.getRelevance() != null){
                relevanceSet.add(dashboardData.getRelevance());
            }
        }

        return relevanceSet;
    }
    //intensity for selected sector
    public List<String> getIntensityBySector(String sector){
        Iterable<dashboard> dashboardDatas = dbrp.findBySector(sector);
        List<String> intensitySet = new ArrayList<>();

        for(dashboard dashboardData: dashboardDatas){
            if(dashboardData.getIntensity() != null){
                intensitySet.add(dashboardData.getIntensity());
            }
        }

        return intensitySet;
    }
    //get url for selected sector and title
    public List<String> getUrlBySectorAndTitle(String sector,String title){
        Iterable<dashboard> dashboardDatas = dbrp.findBySectorAndTitle(sector,title);
        Set<String> urlSet = new HashSet<>();

        for(dashboard dashboardData: dashboardDatas){
            urlSet.add(dashboardData.getUrl());
        }

        return urlSet.stream().collect(Collectors.toList());
    }
    //get title and url for selected sector
    public Map<String,String> getTitleAndUrlBySector(String sector){
        Map<String,String> titleUrlMap = new HashMap<>();
        String selectedSector = sector;
        List<String> titleSet = getTitleBySector(selectedSector);

        for(int i=0;i<titleSet.size();i++){
            String selectedTitle=titleSet.get(i);
            if(selectedTitle != null){
                List<String> urlSet = getUrlBySectorAndTitle(selectedSector,selectedTitle);
                for(int j=0;j<urlSet.size();j++){
                    String selectedUrl = urlSet.get(j);
                    titleUrlMap.put(selectedTitle,selectedUrl);
                }

            }
        }

        return titleUrlMap;
    }
    //COUNTS
    //get sector and no.of titles
    public Map<String,Integer> getTitleAndSector(){
        Map<String,Integer> sectorTitleMap = new HashMap<>();
        List<String> sectorSet = allSector();

        for(int i=0;i<sectorSet.size();i++){
            String selectedSector=sectorSet.get(i);
            if(selectedSector != null){
                List<String> titleSet = getTitleBySector(selectedSector);
                int n = titleSet.size();
                sectorTitleMap.put(selectedSector,n);
            }
        }

        return sectorTitleMap;
    }
    //sector and no.of countries
    public Map<String,Integer> getSectorAndCountryCount(){
        Map<String,Integer> sectorCountryMap = new HashMap<>();
        Set<String> topsector = new HashSet<>();
        topsector = getTopSector();
        List<String> topSectors= new ArrayList<>(topsector);

        for(int i=0;i<topSectors.size();i++){
            String selectedSector=topSectors.get(i);
            if(selectedSector != null){
                List<String>  CountryData = getCountryBySector(selectedSector);
                int n = CountryData.size();
                sectorCountryMap.put(selectedSector,n);
            }
        }

        return sectorCountryMap;
    }
    //sector and count of intensity
    public Map<String,Integer> getSectorAndIntensityCount(){
        Map<String,Integer> intensitySector = new HashMap<>();
        List<String> sectorSet = allSector();

        for(int i=0;i<sectorSet.size();i++){
            String selectedSector=sectorSet.get(i);
            if(selectedSector != null){
                List<String>  intensityData = getIntensityBySector(selectedSector);
                List<Integer> intensityList = new ArrayList<>();
                int sum=0;
                for(String str : intensityData){
                    intensityList.add(Integer.parseInt(str));
                }
                for(int n: intensityList){
                    sum+=n;
                }
                intensitySector.put(selectedSector,sum);
            }
        }
        return intensitySector;
    }
    //sector and count of relevance
    public Map<String,Integer> getSectorAndRelevanceCount(){
        Map<String,Integer> relevanceSector = new HashMap<>();
        List<String> sectorSet = allSector();

        for(int i=0;i<sectorSet.size();i++){
            String selectedSector=sectorSet.get(i);
            if(selectedSector != null){
                List<String>  relevanceData = getRelevanceBySector(selectedSector);
                List<Integer> relevanceList = new ArrayList<>();
                int sum=0;
                for(String str : relevanceData){
                    relevanceList.add(Integer.parseInt(str));
                }
                for(int n: relevanceList){
                    sum+=n;
                }
                relevanceSector.put(selectedSector,sum);
            }
        }
        return relevanceSector;
    }
    //sector and no.of published
    public Map<String,Integer> getSectorAndPublishedCount(){
        Map<String,Integer> sectorPublishedMap = new HashMap<>();
        Set<String> topsector = new HashSet<>();
        topsector = getTopSector();
        List<String> topSectors= new ArrayList<>(topsector);

        for(int i=0;i<topSectors.size();i++){
            String selectedSector=topSectors.get(i);
            if(selectedSector != null){
                List<String>  publishedData = getPublishedBySector(selectedSector);
                int n = publishedData.size();
                sectorPublishedMap.put(selectedSector,n);
            }
        }

        return sectorPublishedMap;
    }
    //sector and no.of swot
    public Map<String,Integer> getSectorAndSwotCount(){
        Map<String,Integer> sectorSwotMap = new HashMap<>();
        Set<String> topsector = new HashSet<>();
        topsector = getTopSector();
        List<String> topSectors= new ArrayList<>(topsector);

        for(int i=0;i<topSectors.size();i++){
            String selectedSector=topSectors.get(i);
            if(selectedSector != null){
                List<String>  swotData = getSwotBySector(selectedSector);
                int n = swotData.size();
                sectorSwotMap.put(selectedSector,n);
            }
        }

        return sectorSwotMap;
    }
    //BASED UPON REGIONS
    //Get all region
    public List<String> allRegion(){
        Iterable<dashboard> dashboardDatas = dbrp.findAll();
        Set<String> regionSet = new HashSet<>();

        for(dashboard dashboardData: dashboardDatas){
            regionSet.add(dashboardData.getRegion());
        }

        return regionSet.stream().collect(Collectors.toList());
    }
    //get title for selected region
    public List<String> getTitleByRegion(String region){
        Iterable<dashboard> dashboardDatas = dbrp.findByRegion(region);
        Set<String> titleSet = new HashSet<>();

        for(dashboard dashboardData: dashboardDatas){
            titleSet.add(dashboardData.getTitle());
        }

        return titleSet.stream().collect(Collectors.toList());
    }
    //get sector for selected region
    public List<String> getSectorByRegion(String region){
        Iterable<dashboard> dashboardDatas = dbrp.findByRegion(region);
        Set<String> sectorSet = new HashSet<>();

        for(dashboard dashboardData: dashboardDatas){
            sectorSet.add(dashboardData.getSector());
        }

        return sectorSet.stream().collect(Collectors.toList());
    }
    //COUNTS
    //get region and no.of titles
    public Map<String,Integer> getTitleAndRegion(){
        Map<String,Integer> regionTitleMap = new HashMap<>();
        List<String> regionSet = allRegion();

        for(int i=0;i<regionSet.size();i++){
            String selectedRegion=regionSet.get(i);
            if(selectedRegion != null){
                List<String> titleSet = getTitleByRegion(selectedRegion);
                int n = titleSet.size();
                regionTitleMap.put(selectedRegion,n);
            }
        }

        return regionTitleMap;
    }
    //get region and no.of sectors
    public Map<String,Integer> getSectorAndRegion(){
        Map<String,Integer> regiionSectorMap = new HashMap<>();
        List<String> regionSet = allRegion();

        for(int i=0;i<regionSet.size();i++){
            String selectedRegion=regionSet.get(i);
            if(selectedRegion != null){
                List<String> sectorSet = getSectorByRegion(selectedRegion);
                int n = sectorSet.size();
                regiionSectorMap.put(selectedRegion,n);
            }
        }

        return regiionSectorMap;
    }
    //BASED UPON RELEVANCE
    //Get all relevance
    public List<String> allRelevance(){
        Iterable<dashboard> dashboardDatas = dbrp.findAll();
        Set<String> relevanceSet = new HashSet<>();

        for(dashboard dashboardData: dashboardDatas){
            relevanceSet.add(dashboardData.getRelevance());
        }

        return relevanceSet.stream().collect(Collectors.toList());
    }
    //get pestle for selected relevance
    public List<String> getPestleByRelevance(String relevance){
        Iterable<dashboard> dashboardDatas = dbrp.findByRelevance(relevance);
        Set<String> pestleSet = new HashSet<>();

        for(dashboard dashboardData: dashboardDatas){
            pestleSet.add(dashboardData.getPestle());
        }

        return pestleSet.stream().collect(Collectors.toList());
    }
    //get swot for selected relevance
    public List<String> getSwotByRelevance(String relevance){
        Iterable<dashboard> dashboardDatas = dbrp.findByRelevance(relevance);
        Set<String> swotSet = new HashSet<>();

        for(dashboard dashboardData: dashboardDatas){
            swotSet.add(dashboardData.getSwot());
        }

        return swotSet.stream().collect(Collectors.toList());
    }
    //COUNT
    //get relevance and no.of pestle
    public Map<String,Integer> getPestleAndRelevance(){
        Map<String,Integer> pestlerelevanceMap = new HashMap<>();
        List<String> relevance = allRelevance();

        for(int i=0;i<relevance.size();i++){
            String selectedRelevance=relevance.get(i);
            if(selectedRelevance != null){
                List<String> pestleset = getPestleByRelevance(selectedRelevance);
                int n = pestleset.size();
                pestlerelevanceMap.put(selectedRelevance,n);
            }
        }

        return pestlerelevanceMap;
    }
    //get relevance and no.of swot
    public Map<String,Integer> getSwotAndRelevance(){
        Map<String,Integer> swotrelevanceMap = new HashMap<>();
        List<String> relevance = allRelevance();

        for(int i=0;i<relevance.size();i++){
            String selectedRelevance=relevance.get(i);
            if(selectedRelevance != null){
                List<String> swotset = getSwotByRelevance(selectedRelevance);
                int n = swotset.size();
                swotrelevanceMap.put(selectedRelevance,n);
            }
        }

        return swotrelevanceMap;
    }
    //BASED UPON PESTLE
    //get all pestle
    public List<String> allPestle(){
        Iterable<dashboard> dashboardDatas = dbrp.findAll();
        Set<String> pestleSet = new HashSet<>();

        for(dashboard dashboardData: dashboardDatas){
            pestleSet.add(dashboardData.getPestle());
        }

        return pestleSet.stream().collect(Collectors.toList());
    }
    // Title for selected pestle
    public List<String> getTopicByPestle(String pestle){
        Iterable<dashboard> dashboardDatas = dbrp.findByPestle(pestle);
        Set<String> topicSet = new HashSet<>();

        for(dashboard dashboardData: dashboardDatas){
            topicSet.add(dashboardData.getTopic());
        }

        return topicSet.stream().collect(Collectors.toList());
    }
    // Swot for selected pestle
    public List<String> getTopicBySwot(String swot){
        Iterable<dashboard> dashboardDatas = dbrp.findBySwot(swot);
        Set<String> topicSet = new HashSet<>();

        for(dashboard dashboardData: dashboardDatas){
            topicSet.add(dashboardData.getTopic());
        }

        return topicSet.stream().collect(Collectors.toList());
    }
    //COUNTS
    // pestle and no.of titles
    public Map<String,Integer> getPestleAndCount(){
        Map<String,Integer> pestleTopicMap = new HashMap<>();
        List<String> pestleSet = allPestle();

        for(int i=0;i<pestleSet.size();i++){
            String selectedPestle=pestleSet.get(i);
            if(selectedPestle != null){
                List<String>  pestleData = getTopicByPestle(selectedPestle);
                int n = pestleData.size();
                pestleTopicMap.put(selectedPestle,n);
            }
        }

        return pestleTopicMap;
    }
    //BASED UPON SWOT
    // all swot
    public List<String> allSwot(){
        Iterable<dashboard> dashboardDatas = dbrp.findAll();
        Set<String> swotSet = new HashSet<>();

        for(dashboard dashboardData: dashboardDatas){
            swotSet.add(dashboardData.getSwot());
        }

        return swotSet.stream().collect(Collectors.toList());
    }
    //COUNTS
    // swot and no.of topics
    public Map<String,Integer> getSwotAndCount(){
        Map<String,Integer> swotTopicMap = new HashMap<>();
        List<String> swotSet = allSwot();

        for(int i=0;i<swotSet.size();i++){
            String selectedSwot=swotSet.get(i);
            if(selectedSwot != null){
                List<String>  swotData = getTopicBySwot(selectedSwot);
                int n = swotData.size();
                swotTopicMap.put(selectedSwot,n);
            }
        }

        return swotTopicMap;
    }
    //BASED UPON TOPICS
    // all topics
    public List<String> allTopic(){
        Iterable<dashboard> dashboardDatas = dbrp.findAll();
        Set<String> topicSet = new HashSet<>();

        for(dashboard dashboardData: dashboardDatas){
            topicSet.add(dashboardData.getTopic());
        }

        return topicSet.stream().collect(Collectors.toList());
    }
    //impact for selected topic
    public List<String> getImpactByTopic(String topic){
        Iterable<dashboard> dashboardDatas = dbrp.findByTopic(topic);
        List<String> topicSet = new ArrayList<>();

        for(dashboard dashboardData: dashboardDatas){
            topicSet.add(dashboardData.getImpact());
        }

        return topicSet;
    }
    //likelihood for selected topic
    public List<String> getLikelihoodByTopic(String topic){
        Iterable<dashboard> dashboardDatas = dbrp.findByTopic(topic);
        List<String> likelihoodSet = new ArrayList<>();

        for(dashboard dashboardData: dashboardDatas){
            likelihoodSet.add(dashboardData.getLikelihood());
        }

        return likelihoodSet;
    }
    //start year for selected topic
    public List<String> getStartYearByTopic(String topic){
        Iterable<dashboard> dashboardDatas = dbrp.findByTopic(topic);
        Set<String> startYearSet = new HashSet<>();

        for(dashboard dashboardData: dashboardDatas){
            if(dashboardData.getStart_year() != null){
                startYearSet.add(dashboardData.getStart_year());
            }
        }

        return startYearSet.stream().collect(Collectors.toList());
    }
    //end year for selected topic
    public List<String> getEndYearByTopic(String topic){
        Iterable<dashboard> dashboardDatas = dbrp.findByTopic(topic);
        Set<String> endYearSet = new HashSet<>();

        for(dashboard dashboardData: dashboardDatas){
            endYearSet.add(dashboardData.getEnd_year());
        }

        return endYearSet.stream().collect(Collectors.toList());
    }
    //added year for selected topic
    public List<String> getAddedYearByTopic(String topic){
        Iterable<dashboard> dashboardDatas = dbrp.findByTopic(topic);
        Set<String> addedYearSet = new HashSet<>();

        for(dashboard dashboardData: dashboardDatas){
            addedYearSet.add(dashboardData.getAdded());
        }

        return addedYearSet.stream().collect(Collectors.toList());
    }
    //source for selected topic
    public List<String> getSourceByTopic(String topic){
        Iterable<dashboard> dashboardDatas = dbrp.findByTopic(topic);
        Set<String> sourceSet = new HashSet<>();

        for(dashboard dashboardData: dashboardDatas){
            if(dashboardData.getSource() != null){
                sourceSet.add(dashboardData.getSource());
            }
        }

        return sourceSet.stream().collect(Collectors.toList());
    }
    //insight for selected topic
    public List<String> getInsightByTopic(String topic){
        Iterable<dashboard> dashboardDatas = dbrp.findByTopic(topic);
        Set<String> insightSet = new HashSet<>();

        for(dashboard dashboardData: dashboardDatas){
            if(dashboardData.getInsight() != null){
                insightSet.add(dashboardData.getInsight());
            }
        }

        return insightSet.stream().collect(Collectors.toList());
    }
    //title for selected topic
    public List<String> getTitleByTopic(String topic){
        Iterable<dashboard> dashboardDatas = dbrp.findByTopic(topic);
        Set<String> titleSet = new HashSet<>();

        for(dashboard dashboardData: dashboardDatas){
            if(dashboardData.getTitle() != null){
                titleSet.add(dashboardData.getTitle());
            }
        }

        return titleSet.stream().collect(Collectors.toList());
    }
    //get source for selected topic and title
    public List<String> getSourceByTopicAndTitle(String topic,String title){
        Iterable<dashboard> dashboardDatas = dbrp.findByTopicAndTitle(topic,title);
        Set<String> sourceSet = new HashSet<>();

        for(dashboard dashboardData: dashboardDatas){
            sourceSet.add(dashboardData.getSource());
        }

        return sourceSet.stream().collect(Collectors.toList());
    }
    //get insight for selected topic and title
    public List<String> getInsightByTopicAndTitle(String topic,String title){
        Iterable<dashboard> dashboardDatas = dbrp.findByTopicAndTitle(topic,title);
        Set<String> insightSet = new HashSet<>();

        for(dashboard dashboardData: dashboardDatas){
            insightSet.add(dashboardData.getInsight());
        }

        return insightSet.stream().collect(Collectors.toList());
    }
    //get title and source for selected topic
    public Map<String,String> getTitleAndSourceByTopic(String topic){
        Map<String,String> titleSourceMap = new HashMap<>();
        String selectedTopic = topic;
        List<String> titleSet = getTitleByTopic(selectedTopic);

        for(int i=0;i<titleSet.size();i++){
            String selectedTitle=titleSet.get(i);
            if(selectedTitle != null){
                List<String> sourceSet = getSourceByTopicAndTitle(selectedTopic,selectedTitle);
                for(int j=0;j<sourceSet.size();j++){
                    String selectedSource = sourceSet.get(j);
                    titleSourceMap.put(selectedTitle,selectedSource);
                }

            }
        }

        return titleSourceMap;
    }
    //get title and insight for selected topic
    public Map<String,String> getTitleAndInsightByTopic(String topic){
        Map<String,String> titleInsightMap = new HashMap<>();
        String selectedTopic = topic;
        List<String> titleSet = getTitleByTopic(selectedTopic);

        for(int i=0;i<titleSet.size();i++){
            String selectedTitle=titleSet.get(i);
            if(selectedTitle != null){
                List<String> insightSet = getInsightByTopicAndTitle(selectedTopic,selectedTitle);
                for(int j=0;j<insightSet.size();j++){
                    String selectedInsight = insightSet.get(j);
                    titleInsightMap.put(selectedTitle,selectedInsight);
                }

            }
        }

        return titleInsightMap;
    }

    //COUNT
    //topic and count of impact
    public Map<String,Integer> getimpactCount(){
        Map<String,Integer> impactTopic = new HashMap<>();
        List<String> topicSet = allTopic();

        for(int i=0;i<topicSet.size();i++){
            String selectedTopic=topicSet.get(i);
            if(selectedTopic != null){
                List<String>  impactData = getImpactByTopic(selectedTopic);
                int n1 = impactData.size();
                impactTopic.put(selectedTopic,n1);
            }
        }
        return impactTopic;
    }
    //topic and likelihood count
    public Map<String,Integer> getLikelihoodCount(){
        Map<String,Integer> likelihoodTopic = new HashMap<>();
        List<String> topicSet = allTopic();

        for(int i=0;i<topicSet.size();i++){
            String selectedTopic=topicSet.get(i);
            if(selectedTopic != null){
                List<String> likelihoodData = getLikelihoodByTopic(selectedTopic);
                int n2 = likelihoodData.size();
                likelihoodTopic.put(selectedTopic, n2);
            }
        }
        return likelihoodTopic;
    }
    //topic and count of start year
    public Map<String,Integer> getStartYearCount(){
        Map<String,Integer> endYearTopic = new HashMap<>();
        List<String> topicSet = allTopic();

        for(int i=0;i<topicSet.size();i++){
            String selectedTopic=topicSet.get(i);
            if(selectedTopic != null){
                List<String>  endYearData = getStartYearByTopic(selectedTopic);
                int n1 = endYearData.size();
                endYearTopic.put(selectedTopic,n1);
            }
        }
        return endYearTopic;
    }
    //topic and count of end year
    public Map<String,Integer> getEndYearCount(){
        Map<String,Integer> startYearTopic = new HashMap<>();
        List<String> topicSet = allTopic();

        for(int i=0;i<topicSet.size();i++){
            String selectedTopic=topicSet.get(i);
            if(selectedTopic != null){
                List<String>  startYearData = getEndYearByTopic(selectedTopic);
                int n1 = startYearData.size();
                startYearTopic.put(selectedTopic,n1);
            }
        }
        return startYearTopic;
    }
    //topic and count of added year
    public Map<String,Integer> getAddedYearCount(){
        Map<String,Integer> addedYearTopic = new HashMap<>();
        List<String> topicSet = allTopic();

        for(int i=0;i<topicSet.size();i++){
            String selectedTopic=topicSet.get(i);
            if(selectedTopic != null){
                List<String>  addedYearData = getAddedYearByTopic(selectedTopic);
                int n1 = addedYearData.size();
                addedYearTopic.put(selectedTopic,n1);
            }
        }
        return addedYearTopic;
    }

    //BASED UPON CITY
    //All City
    public List<String> allCity(){
        Iterable<dashboard> dashboardDatas = dbrp.findAll();
        Set<String> citySet = new HashSet<>();

        for(dashboard dashboardData: dashboardDatas){
            citySet.add(dashboardData.getCity());
        }

        return citySet.stream().collect(Collectors.toList());
    }
    //relevance for selected city
    public List<String> getRelevanceByCity(String city){
        Iterable<dashboard> dashboardDatas = dbrp.findByCity(city);
        List<String> relevanceSet = new ArrayList<>();

        for(dashboard dashboardData: dashboardDatas){
            if(dashboardData.getRelevance() != null){
                relevanceSet.add(dashboardData.getRelevance());
            }
        }

        return relevanceSet;
    }
    //intensity for selected city
    public List<String> getIntensityByCity(String city){
        Iterable<dashboard> dashboardDatas = dbrp.findByCity(city);
        List<String> intensitySet = new ArrayList<>();

        for(dashboard dashboardData: dashboardDatas){
            if(dashboardData.getIntensity() != null){
                intensitySet.add(dashboardData.getIntensity());
            }
        }

        return intensitySet;
    }
    //COUNT
    //city and count of relevance
    public Map<String,Integer> getCityAndRelevanceCount(){
        Map<String,Integer> relevanceCity = new HashMap<>();
        List<String> citySet = allCity();

        for(int i=0;i<citySet.size();i++){
            String selectedCity=citySet.get(i);
            if(selectedCity != null){
                List<String>  relevanceData = getRelevanceByCity(selectedCity);
                List<Integer> relevanceList = new ArrayList<>();
                int sum=0;
                for(String str: relevanceData){
                    relevanceList.add(Integer.parseInt(str));
                }
                for(int n : relevanceList){
                    sum+=n;
                }
                relevanceCity.put(selectedCity,sum);
            }
        }
        return relevanceCity;
    }
    //city and count of intensity
    public Map<String,Integer> getCityAndIntensityCount(){
        Map<String,Integer> intensityCity = new HashMap<>();
        List<String> citySet = allCity();

        for(int i=0;i<citySet.size();i++){
            String selectedCity=citySet.get(i);
            if(selectedCity != null){
                List<String>  intensityData
                        = getIntensityByCity(selectedCity);
                List<Integer> intensityList = new ArrayList<>();
                int sum=0;
                for(String str : intensityData){
                    intensityList.add(Integer.parseInt(str));
                }
                for(int n: intensityList){
                    sum+=n;
                }
                intensityCity.put(selectedCity,sum);
            }
        }
        return intensityCity;
    }
    //BASED UPON COUNTRY
    //Get by Country
    public Iterable<dashboard> getByCountry(String country){
        return dbrp.findByCountry(country);
    }
    //All Country
    public List<String> allCountry(){
        Iterable<dashboard> dashboardDatas = dbrp.findAll();
        Set<String> countrySet = new HashSet<>();

        for(dashboard dashboardData: dashboardDatas){
            countrySet.add(dashboardData.getCountry());
        }

        return countrySet.stream().collect(Collectors.toList());
    }
    //get pestle for selected country
    public List<String> getPestleByCountry(String country){
        Iterable<dashboard> dashboardDatas = dbrp.findByCountry(country);
        Set<String> pestleSet = new HashSet<>();

        for(dashboard dashboardData: dashboardDatas){
            pestleSet.add(dashboardData.getPestle());
        }

        return pestleSet.stream().collect(Collectors.toList());
    }
    //get swot for selected country
    public List<String> getSwotByCountry(String country){
        Iterable<dashboard> dashboardDatas = dbrp.findByCountry(country);
        Set<String> swotSet = new HashSet<>();

        for(dashboard dashboardData: dashboardDatas){
            swotSet.add(dashboardData.getSwot());
        }

        return swotSet.stream().collect(Collectors.toList());
    }
    //get country and no.of pestle
    public Map<String,Integer> getPestleAndCountry(){
        Map<String,Integer> pestleCountryMap = new HashMap<>();
        List<String> country = allCountry();

        for(int i=0;i<country.size();i++){
            String selectedCountry=country.get(i);
            if(selectedCountry != null){
                List<String> pestleset = getPestleByCountry(selectedCountry);
                int n = pestleset.size();
                pestleCountryMap.put(selectedCountry,n);
            }
        }

        return pestleCountryMap;
    }
    //get country and no.of swot
    public Map<String,Integer> getSwotAndCountry(){
        Map<String,Integer> swotCountryMap = new HashMap<>();
        List<String> country = allCountry();

        for(int i=0;i<country.size();i++){
            String selectedCountry=country.get(i);
            if(selectedCountry != null){
                List<String> swotset = getSwotByCountry(selectedCountry);
                int n = swotset.size();
                swotCountryMap.put(selectedCountry,n);
            }
        }
        return swotCountryMap;
    }
}
