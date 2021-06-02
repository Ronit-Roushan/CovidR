package com.example.covidtracker;

public class state {
    String region,activeCases,newInfected,recovered,newRecovered,deceased,newDeceased,totalInfected;
    public state(String region,String activeCases, String newInfected, String recovered, String newRecovered, String deceased, String newDeceased, String totalInfected) {
        this.activeCases = activeCases;
        this.region = region;
        this.newInfected = newInfected;
        this.recovered = recovered;
        this.newRecovered = newRecovered;
        this.deceased = deceased;
        this.newDeceased = newDeceased;
        this.totalInfected = totalInfected;
    }



}
