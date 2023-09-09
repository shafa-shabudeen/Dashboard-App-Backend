package com.example.dashboard.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name="dashboard",schema = "data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class dashboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;
    private String title;
    private String source;
    private String sector;
    private String insight;
    private String url;
    private String start_year;
    private String end_year;
    private String published;
    private String added;
    private String region;
    private String city;
    private String citylng;
    private String citylat;
    private String country;
    private String pestle;
    private String relevance;
    private String intensity;
    private String impact;
    private String likelihood;
    private String swot;

}
